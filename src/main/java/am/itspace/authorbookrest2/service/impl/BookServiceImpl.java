package am.itspace.authorbookrest2.service.impl;

import am.itspace.authorbookrest2.dto.BookDto;
import am.itspace.authorbookrest2.dto.BookFilterDto;
import am.itspace.authorbookrest2.dto.CBCurrencyResponseDto;
import am.itspace.authorbookrest2.dto.SaveBookDto;
import am.itspace.authorbookrest2.entity.Book;
import am.itspace.authorbookrest2.entity.QBook;
import am.itspace.authorbookrest2.mapper.BookMapper;
import am.itspace.authorbookrest2.repository.AuthorRepository;
import am.itspace.authorbookrest2.repository.BookRepository;
import am.itspace.authorbookrest2.service.BookService;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final RestTemplate restTemplate;
    private final EntityManager entityManager;

    @Override
    public BookDto save(SaveBookDto saveBookDto) {
        Book book = bookMapper.map(saveBookDto);
        book.setAuthor(authorRepository.findById(saveBookDto.getAuthorId()).orElse(null));
        bookRepository.save(book);
        return bookMapper.map(book);

    }

    @Override
    public List<BookDto> getAll() {
        List<Book> all = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        if (!all.isEmpty()) {
            double usdCurrency = getUsdCurrency();
            double rubCurrency = getRubCurrency();

            for (Book book : all) {
                BookDto bookDto = bookMapper.map(book);
                setUsdPrice(bookDto, usdCurrency);
                setRubPrice(bookDto, rubCurrency);
                bookDtos.add(bookDto);
            }
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> getAllByFilter(BookFilterDto bookFilterDto) {
        JPAQuery<Book> query = new JPAQuery(entityManager);

        QBook qBook = QBook.book;
        JPAQueryBase from = query.from(qBook);

        if (StringUtils.isNotBlank(bookFilterDto.getTitle())) {
            from.where(qBook.title.contains(bookFilterDto.getTitle()));
        }

        if (StringUtils.isNotBlank(bookFilterDto.getDescription())) {
            from.where(qBook.description.contains(bookFilterDto.getDescription()));
        }

        if (bookFilterDto.getMinPrice() != null && bookFilterDto.getMaxPrice() != null) {
            from.where(qBook.price.between(bookFilterDto.getMinPrice(), bookFilterDto.getMaxPrice()));
        } else if (bookFilterDto.getMinPrice() != null) {
            from.where(qBook.price.goe(bookFilterDto.getMinPrice()));
        } else if (bookFilterDto.getMaxPrice() != null) {
            from.where(qBook.price.loe(bookFilterDto.getMaxPrice()));
        }

        if (bookFilterDto.getPage() > 0) {
            from.offset((long) bookFilterDto.getPage() * bookFilterDto.getSize());
        }
        from.limit(bookFilterDto.getSize());

        PathBuilder<Object> orderByExpression = new PathBuilder<Object>(Book.class, bookFilterDto.getOrderBy());

        from.orderBy(new OrderSpecifier("asc".equalsIgnoreCase(bookFilterDto.getOrderDirection()) ? Order.ASC
                : Order.DESC, orderByExpression));


        List<Book> books = query.fetch();

        return bookMapper.map(books);
    }


    private void setRubPrice(BookDto bookDto, double rubCurrency) {
        bookDto.setPriceRUB(bookDto.getPrice() / rubCurrency);
    }


    private void setUsdPrice(BookDto bookDto, double usdCurrency) {
        bookDto.setPriceUSD(bookDto.getPrice() / usdCurrency);
    }

    private double getUsdCurrency() {
        ResponseEntity<CBCurrencyResponseDto> forEntity = restTemplate.getForEntity
                ("https://cb.am//latest.json.php", CBCurrencyResponseDto.class);
        if (forEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
            CBCurrencyResponseDto body = forEntity.getBody();
            return Double.parseDouble(body.getUsd());
        }
        return 0;
    }

    private double getRubCurrency() {
        ResponseEntity<CBCurrencyResponseDto> forEntity = restTemplate.getForEntity
                ("https://cb.am//latest.json.php", CBCurrencyResponseDto.class);
        if (forEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
            CBCurrencyResponseDto body = forEntity.getBody();
            return Double.parseDouble(body.getRub());
        }
        return 0;
    }
}


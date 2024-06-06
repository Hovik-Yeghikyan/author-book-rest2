package am.itspace.authorbookrest2.repository;

import am.itspace.authorbookrest2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}

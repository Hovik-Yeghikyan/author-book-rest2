package am.itspace.authorbookrest2.repository;

import am.itspace.authorbookrest2.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author,Integer> {

}

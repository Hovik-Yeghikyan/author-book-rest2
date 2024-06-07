package am.itspace.authorbookrest2.repository;

import am.itspace.authorbookrest2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {

    Optional<User> findByEmail(String email);

}

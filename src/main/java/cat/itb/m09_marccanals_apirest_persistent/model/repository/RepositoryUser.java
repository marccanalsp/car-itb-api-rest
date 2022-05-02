package cat.itb.m09_marccanals_apirest_persistent.model.repository;

import cat.itb.m09_marccanals_apirest_persistent.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

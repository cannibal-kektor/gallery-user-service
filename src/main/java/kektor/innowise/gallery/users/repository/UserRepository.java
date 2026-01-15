package kektor.innowise.gallery.users.repository;

import kektor.innowise.gallery.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);

}

package green.energy.kafkapersister.data;

import green.energy.kafkapersister.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}

package green.energy.rabbitaggregator.data;

import green.energy.rabbitaggregator.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}

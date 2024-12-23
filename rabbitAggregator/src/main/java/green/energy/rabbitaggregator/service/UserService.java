package green.energy.rabbitaggregator.service;

import green.energy.rabbitaggregator.data.UserRepository;
import green.energy.rabbitaggregator.data.entity.User;
import green.energy.rabbitaggregator.model.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        userOptional.orElseThrow(() -> new UserNotFoundException("User %s not found".formatted(username)));

        return userOptional.get();
    }
}

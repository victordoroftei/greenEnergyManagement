package green.energy.kafkapersister.service;

import green.energy.kafkapersister.data.UserRepository;
import green.energy.kafkapersister.data.entity.User;
import green.energy.kafkapersister.model.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}

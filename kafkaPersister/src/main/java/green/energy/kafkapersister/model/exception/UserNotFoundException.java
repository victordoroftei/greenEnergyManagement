package green.energy.kafkapersister.model.exception;

public class UserNotFoundException extends  RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}

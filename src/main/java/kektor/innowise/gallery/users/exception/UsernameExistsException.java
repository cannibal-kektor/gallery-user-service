package kektor.innowise.gallery.users.exception;

public class UsernameExistsException extends RuntimeException {

    private static final String USERNAME_EXISTS = "Submitted username (%s) already exists";

    public UsernameExistsException(String username) {
        super(String.format(USERNAME_EXISTS, username));
    }
}

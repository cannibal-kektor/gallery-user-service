package kektor.innowise.gallery.users.exception;

public class UsernameNotFoundException extends RuntimeException {

    private static final String USERNAME_NOT_FOUND = "User with username: (%s) not found";

    public UsernameNotFoundException(String username) {
        super(String.format(USERNAME_NOT_FOUND, username));
    }
}

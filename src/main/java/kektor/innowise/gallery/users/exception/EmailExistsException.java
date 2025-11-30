package kektor.innowise.gallery.users.exception;

public class EmailExistsException extends RuntimeException {

    private static final String EMAIL_EXISTS = "Submitted email (%s) is already used";

    public EmailExistsException(String email) {
        super(String.format(EMAIL_EXISTS, email));
    }
}

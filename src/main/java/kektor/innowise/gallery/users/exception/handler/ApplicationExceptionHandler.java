package kektor.innowise.gallery.users.exception.handler;

import kektor.innowise.gallery.users.exception.EmailExistsException;
import kektor.innowise.gallery.users.exception.UserNotFoundException;
import kektor.innowise.gallery.users.exception.UsernameExistsException;
import kektor.innowise.gallery.users.exception.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;


@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmailExistsException.class, UsernameExistsException.class})
    public ErrorResponse handleRegistrationDataConflict(Exception ex) {
        return ErrorResponse.create(ex, HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class, UserNotFoundException.class})
    public ErrorResponse handleUsernameNotFound(Exception ex) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RestClientResponseException.class)
    ResponseEntity<ProblemDetail> handleRestClientResponseException(RestClientResponseException ex) {
        ProblemDetail detail = ex.getResponseBodyAs(ProblemDetail.class);
        return ResponseEntity.status(ex.getStatusCode()).body(detail);
    }

    @ExceptionHandler({DataAccessException.class, DbActionExecutionException.class})
    public ErrorResponse handleInvalidDatabaseRequestException(Exception ex) {
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ErrorResponse handleAll(Exception ex) {
        return ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, Optional.ofNullable(ex.getMessage())
                .orElse("Internal server error"));
    }

}

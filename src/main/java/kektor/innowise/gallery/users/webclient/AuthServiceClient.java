package kektor.innowise.gallery.users.webclient;

import kektor.innowise.gallery.users.dto.StorePasswordRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "/api/auth")
public interface AuthServiceClient {

    @PostExchange(url = "/password", contentType = MediaType.APPLICATION_JSON_VALUE)
    void storePassword(@RequestBody StorePasswordRequest storePasswordRequest);

}

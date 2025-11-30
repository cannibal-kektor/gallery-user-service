package kektor.innowise.gallery.users.config;


import kektor.innowise.gallery.security.conf.client.ProtectedAuthenticationServiceClient;
import kektor.innowise.gallery.users.webclient.AuthServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public AuthServiceClient authenticationServiceClient(@ProtectedAuthenticationServiceClient RestClient authRestClient) {
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(authRestClient))
                .build()
                .createClient(AuthServiceClient.class);
    }

}

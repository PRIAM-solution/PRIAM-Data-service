package priam.data.priamdataservice.security;

//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.apache.catalina.filters.CorsFilter;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@KeycloakConfiguration
public class keycloakAdapterConfig {
    @Bean
    KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory) {
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }

}

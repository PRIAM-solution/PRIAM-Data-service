package priam.data.priamdataservice;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import priam.data.priamdataservice.repositories.DataRepository;

@SpringBootApplication
@Configuration
@EnableFeignClients
public class PriamDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriamDataServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(DataRepository dataRepository) {
        return args -> {
        };

    }
}

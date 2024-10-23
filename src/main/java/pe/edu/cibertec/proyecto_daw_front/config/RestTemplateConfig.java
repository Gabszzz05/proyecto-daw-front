package pe.edu.cibertec.proyecto_daw_front.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplateListado(RestTemplateBuilder builder) {
        return builder
                .rootUri("https://app-proyecto-backend.azurewebsites.net/auth")
                .build();
    }

}
//rootUri => http://localhost:8181/auth
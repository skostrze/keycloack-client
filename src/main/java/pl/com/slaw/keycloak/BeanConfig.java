package pl.com.slaw.keycloak;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver()
    {
        return new KeycloakSpringBootConfigResolver();
    }
}

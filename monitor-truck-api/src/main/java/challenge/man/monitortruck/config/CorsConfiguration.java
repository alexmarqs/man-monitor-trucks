package challenge.man.monitortruck.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Cors configuration.
 */
@Configuration
public class CorsConfiguration {

    /**
     * The Allowed cors origins.
     */
    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private String[] allowedCorsOrigins;

    /**
     * Cors configurer.
     *
     * @return the configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedCorsOrigins)
                        .maxAge(3600);
            }
        };
    }

}

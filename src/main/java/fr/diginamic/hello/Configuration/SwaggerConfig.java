package fr.diginamic.hello.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Recensement TP")
                        .version("1.0")
                        .description("Cette api est un tp qui fourni des données de recensement de la population pour" +
                                " la France")
                );
    }
}

package br.com.fcamara.fcamaraestacionamentos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Camara Estacionamentos")
                        .description("Sistema de gerenciamento de estacionamento")
                        .version("0.0.1"));
    }

}

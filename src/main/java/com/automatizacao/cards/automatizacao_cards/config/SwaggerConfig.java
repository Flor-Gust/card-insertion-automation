package com.automatizacao.cards.automatizacao_cards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("API para importação de notas no Anki de forma automática")
            .version("1.0.o")
            .description("API para importação de notas no Anki de forma automática usando arquivos 'txt'"));
  }
}

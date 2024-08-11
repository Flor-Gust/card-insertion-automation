package com.automatizacao.cards.automatizacao_cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AutomatizacaoCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatizacaoCardsApplication.class, args);
	}

}

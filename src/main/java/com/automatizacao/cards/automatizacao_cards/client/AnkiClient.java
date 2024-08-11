package com.automatizacao.cards.automatizacao_cards.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.automatizacao.cards.automatizacao_cards.model.AnkiRequest;

@FeignClient(name = "anki", url = "http://localhost:8765")
public interface AnkiClient {
 
  @PostMapping 
  ResponseEntity<String> addNotes(@RequestBody AnkiRequest request);
}

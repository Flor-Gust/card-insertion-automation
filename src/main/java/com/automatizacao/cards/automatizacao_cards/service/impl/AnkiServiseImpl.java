package com.automatizacao.cards.automatizacao_cards.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.automatizacao.cards.automatizacao_cards.client.AnkiClient;
import com.automatizacao.cards.automatizacao_cards.model.AnkiRequest;
import com.automatizacao.cards.automatizacao_cards.model.Notes;
import com.automatizacao.cards.automatizacao_cards.model.Params;
import com.automatizacao.cards.automatizacao_cards.service.AnkiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnkiServiseImpl implements AnkiService {

  @Autowired
  private AnkiClient ankiClient;

  @Override
  public void addNotesFromFile(String filePath, String deckName, String modelName) {
    List<Notes> notes = readNotesrsFromFile(filePath);
    notes.forEach(n -> {
      n.setDeckName(deckName);
      n.setModelName(modelName);
      n.setTags(new String[] { "1xi" });
    });
    sendNotesToAnki(notes);
  }

  private List<Notes> readNotesrsFromFile(String filePath) {
    try {
      File file = new File(filePath);
      List<String> lines = Files.readAllLines(file.toPath());
      return lines.stream().map(Notes::parseNotes).collect(Collectors.toList());
    } catch (IOException ex) {
      throw new RuntimeException("Error reading file", ex);
    }
  }

  @Override
  public void sendNotesToAnki(List<Notes> notes) {
    AnkiRequest ankiRequest = new AnkiRequest();
    ankiRequest.setAction("addNotes");
    ankiRequest.setVersion(6);

    ankiRequest.setParams(new Params(notes));

    try {
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(ankiRequest);
      log.info("Modelo de mensagem a ser enviada para requisição: {}", json);
    } catch (JsonProcessingException ex) {
      
    }

    ResponseEntity<String> response = ankiClient.addNotes(ankiRequest);
    log.info("Resposta da requisição: {}", response.toString());
  }
}

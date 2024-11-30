package com.automatizacao.cards.automatizacao_cards.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
  public void addNotesFromFile(MultipartFile file, String deckName, String modelName) {
    List<Notes> notes = readNotesrsFromFile(file);
    notes.forEach(n -> {
      n.setDeckName(deckName);
      n.setModelName(modelName);
      n.setTags(new String[] { "1xi" });
    });
    sendNotesToAnki(notes);
  }

  private List<Notes> readNotesrsFromFile(MultipartFile file) {
    ValidateFile(file);

    try {
      List<String> lines = IOUtils.readLines(file.getInputStream(), "UTF-8"); 
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

  void ValidateFile(MultipartFile file) {
    if (file == null || file.isEmpty()) {
      throw new RuntimeException("File is required");
    }

    if(!file.getOriginalFilename().endsWith(".txt")) {
      throw new RuntimeException("Invalid file extension");
    }
  }
}

package com.automatizacao.cards.automatizacao_cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.service.AnkiService;
import com.automatizacao.cards.automatizacao_cards.swagger.AnkiControllerDocumentation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/anki")
@Slf4j
public class AnkiController implements AnkiControllerDocumentation {

  @Autowired
  private AnkiService ankiService;

  @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> importNotes(
      @RequestPart(required = true) MultipartFile file,
      @RequestParam String deckName,
      @RequestParam String modelName) {
    try {
      ankiService.addNotesFromFile(file, deckName, modelName);
      return ResponseEntity.ok().build();
    } catch (Exception ex) {
      log.error("Error importing notes", ex);
      return ResponseEntity.status(500).build();
    }
  }

}

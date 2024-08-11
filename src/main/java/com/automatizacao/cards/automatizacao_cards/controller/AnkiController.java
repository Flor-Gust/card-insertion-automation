package com.automatizacao.cards.automatizacao_cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automatizacao.cards.automatizacao_cards.service.AnkiService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/anki")
@Slf4j
public class AnkiController {

  @Autowired
  private AnkiService ankiService;

  @PostMapping("/import")
  public ResponseEntity<Void> importNotes(@RequestParam String filePath,
      @RequestParam String deckName,
      @RequestParam String modelName) {
    try {
      ankiService.addNotesFromFile(filePath, deckName, modelName);
      return ResponseEntity.ok().build();
    } catch (Exception ex) {
      log.error("Error importing notes", ex);
      return ResponseEntity.status(500).build();
    }
  }

}

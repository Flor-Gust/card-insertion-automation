package com.automatizacao.cards.automatizacao_cards.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.model.Notes;
import com.automatizacao.cards.automatizacao_cards.service.AnkiService;
import com.automatizacao.cards.automatizacao_cards.swagger.AnkiControllerDocumentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/anki")
@RequiredArgsConstructor
@Slf4j
public class AnkiController implements AnkiControllerDocumentation {

  private final AnkiService ankiService;

  @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<Notes>> importNotes(
      @RequestPart(required = true) MultipartFile file,
      @RequestParam String deckName,
      @RequestParam String modelName) {
    try {
      List<Notes> notes = ankiService.sendNotesToAnki(file, deckName, modelName);
      return ResponseEntity.ok().body(notes);
    } catch (Exception ex) {
      log.error("Error importing notes", ex);
      return ResponseEntity.status(500).build();
    }
  }

}

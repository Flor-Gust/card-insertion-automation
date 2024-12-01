package com.automatizacao.cards.automatizacao_cards.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.model.Notes;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;

public interface AnkiControllerDocumentation {

  @PostMapping("/import")
  ResponseEntity<List<Notes>> importNotes(
    @Parameter(description = "File to import", content = @Content(mediaType = "multipart/form-data"))    
    @RequestParam MultipartFile file,
    @RequestParam String deckName,
    @RequestParam String modelName); 
} 
package com.automatizacao.cards.automatizacao_cards.api.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.model.Notes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface AnkiControllerDocumentation {

  @PostMapping("/import")
  @Operation (
    summary = "Automatização de inserção de cards básicos no ANKI",
    description = "Automatização de inserção de cards básicos no ANKI",
    tags = {"Automatização de inserção de cards"},
    responses = {
      @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
  )
  ResponseEntity<List<Notes>> importNotes(
    @Parameter(description = "File to import", content = @Content(mediaType = "multipart/form-data"))    
    @RequestParam MultipartFile file,
    @RequestParam String deckName,
    @RequestParam String modelName); 
} 
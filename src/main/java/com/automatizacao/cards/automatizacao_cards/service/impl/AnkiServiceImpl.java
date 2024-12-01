package com.automatizacao.cards.automatizacao_cards.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.client.AnkiClient;
import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception.CommonExceptionBadRequest;
import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception.CommonExceptionNotFound;
import com.automatizacao.cards.automatizacao_cards.model.AnkiRequest;
import com.automatizacao.cards.automatizacao_cards.model.Fields;
import com.automatizacao.cards.automatizacao_cards.model.Notes;
import com.automatizacao.cards.automatizacao_cards.model.Params;
import com.automatizacao.cards.automatizacao_cards.service.AnkiService;
import com.automatizacao.cards.automatizacao_cards.utils.Validations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnkiServiceImpl implements AnkiService {

  private static final String TYPE_STRING = "UTF-8";
  private static final String ADD_NOTES_ACTION = "addNotes";
  private static final int ANKI_VERSION = 6;
  private static final String[] TAG = new String[] { "1xi" };

  private final AnkiClient ankiClient;

 @Override
  public List<Notes> sendNotesToAnki(MultipartFile file, String deckName, String modelName) {
    List<Notes> notes = buildNotes(file, deckName, modelName);

    AnkiRequest ankiRequest = new AnkiRequest();
    ankiRequest.setAction(ADD_NOTES_ACTION);
    ankiRequest.setVersion(ANKI_VERSION);
    ankiRequest.setParams(new Params(notes));

    try {
      // Converte o ankiRequest para JSON
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(ankiRequest);
      log.info("Json send to Anki: {}", json);

      // Envia a requisição ao Anki e captura a resposta
      ResponseEntity<String> response = ankiClient.addNotes(ankiRequest);
      log.info("Response to Anki: {}", response.toString());

      // Verifica se houve erro no Anki (exemplo: deck ou modelo não encontrado)
      String responseBody = Optional.ofNullable(response.getBody()).orElse("");
      if (responseBody.contains("not found")) {
        throw new CommonExceptionNotFound("Error Not Found deck or model");
      }

    } catch (JsonProcessingException ex) {
      throw new CommonExceptionBadRequest("Error processing JSON: " + ex.getMessage());
    } catch (Exception ex) {
      log.error("Error sending JSON to Anki: {}", ex.getMessage());
      throw new CommonExceptionBadRequest("Error sending data to Anki: " + ex.getMessage());
    }

    return notes;
  }

  private List<Notes> buildNotes(MultipartFile file, String deckName, String modelName) {
    try {
      List<Fields> fields = IOUtils.readLines(file.getInputStream(), TYPE_STRING)
          .stream().map(this::buildFields).toList();

      return fields.stream()
          .map(field -> Notes.builder()
              .deckName(deckName)
              .modelName(modelName)
              .fields(field)
              .tags(TAG)
              .build())
          .toList();
    } catch (IOException ex) {
      throw new CommonExceptionBadRequest("Error reading file" + ex);
    }
  }

  private Fields buildFields(String line) {
    String[] parts = line.split(";");
    if (parts.length != 4) {
      throw new CommonExceptionBadRequest("Invalid input format. Expected 4 parts separated by ';'");
    }
    return Fields.builder()
        .front(Validations.formatWordInBold(parts[0], parts[2]))
        .back(Validations.formatWordInBold(parts[1], parts[3]))
        .build();
  }
}

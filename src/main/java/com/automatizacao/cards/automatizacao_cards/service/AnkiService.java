package com.automatizacao.cards.automatizacao_cards.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.model.Notes;

public interface AnkiService {
  void addNotesFromFile(MultipartFile file, String deckName, String modelName);

  void sendNotesToAnki(List<Notes> Notes);
}

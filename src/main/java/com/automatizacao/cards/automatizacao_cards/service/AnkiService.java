package com.automatizacao.cards.automatizacao_cards.service;

import java.util.List;

import com.automatizacao.cards.automatizacao_cards.model.Notes;

public interface AnkiService {
  void addNotesFromFile(String filePath, String deckName, String modelName);

  void sendNotesToAnki(List<Notes> Notes);
}

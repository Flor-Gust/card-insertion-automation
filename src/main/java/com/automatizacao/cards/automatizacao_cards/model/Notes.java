package com.automatizacao.cards.automatizacao_cards.model;

import com.automatizacao.cards.automatizacao_cards.utils.Validations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notes {
  private String deckName;
  private String modelName;
  private Fields fields;
  private String[] tags;

  public static Notes parseNotes(String line) {
    String[] parts = line.split(";");
    final String front = parts[0];
    final String back = parts[1];
    final String wordToBoldFront = parts[2];
    final String wordToBoldBack = parts[3];

    return Notes.builder()
        .fields(
            Fields.builder()
                .front(Validations.formatWordInBold(front, wordToBoldFront))
                .back(Validations.formatWordInBold(back, wordToBoldBack))
                .build())
        .build();

  }
}

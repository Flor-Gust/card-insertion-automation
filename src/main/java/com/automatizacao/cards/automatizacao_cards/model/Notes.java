package com.automatizacao.cards.automatizacao_cards.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notes {
  private String deckName;
  private String modelName;
  private Fields fields;
  private String[] tags;
}

package com.automatizacao.cards.automatizacao_cards.model;

import lombok.Data;

@Data
public class AnkiRequest {
  private String action;
  private int version;
  private Params params;
}

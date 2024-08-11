package com.automatizacao.cards.automatizacao_cards.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fields {
  @JsonProperty("Front")
  private String front;
  @JsonProperty("Back")
  private String back;
}

package com.automatizacao.cards.automatizacao_cards.utils;

public class Validations {
  
  public static String formatWordInBold(String text, String word) {
    return text.replace(word, "<b>" + word + "</b>");
  }
}

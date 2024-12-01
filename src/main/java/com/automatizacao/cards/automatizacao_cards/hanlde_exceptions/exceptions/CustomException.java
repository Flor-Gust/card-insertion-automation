package com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Date horario;
    private String mensagem;
    private String detials;
}

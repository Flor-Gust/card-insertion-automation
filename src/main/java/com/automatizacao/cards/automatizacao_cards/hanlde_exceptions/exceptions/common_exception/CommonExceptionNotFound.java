package com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommonExceptionNotFound extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public CommonExceptionNotFound(String ex) {
        super(ex);
    }
}

package com.automatizacao.cards.automatizacao_cards.hanlde_exceptions;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.CustomException;
import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception.CommonExceptionBadRequest;
import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception.CommonExceptionNotFound;

@ControllerAdvice
@RestController
public class HandleException extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomException> handleAllExceptions(Exception ex, WebRequest request) {
		CustomException error = new CustomException(
				new Date(),
				"Internal fail",
				request.getDescription(false)
		);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CommonExceptionBadRequest.class)
	public final ResponseEntity<CustomException> handleBadRequestExceptions(CommonExceptionBadRequest ex, WebRequest request) {
		CustomException error = new CustomException(
				new Date(),
				ex.getMessage(), 
				request.getDescription(false)
		);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommonExceptionNotFound.class)
	public final ResponseEntity<CustomException> handleBadRequestExceptions(CommonExceptionNotFound ex, WebRequest request) {
		CustomException error = new CustomException(
				new Date(),
				ex.getMessage(), 
				request.getDescription(false)
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}

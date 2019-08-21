package com.cassio.player.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cassio.player.exceptions.ErroInesperadoException;

@ControllerAdvice
public class ErroInesperadoAdvice {

	@ResponseBody
	@ExceptionHandler(ErroInesperadoException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String erroInesperadoHandler(ErroInesperadoException ex) {
		return ex.getMessage();
	}
}

package com.cassio.player.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cassio.player.exceptions.ClientesNaoEncontradosException;

@ControllerAdvice
public class ClientesNaoEncontradosAdvice {

	@ResponseBody
	@ExceptionHandler(ClientesNaoEncontradosException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	String clientesNaoEncontradosHandler(ClientesNaoEncontradosException ex) {
		return ex.getMessage();
	}
}

package com.cassio.player.advices;

import com.cassio.player.exceptions.ClientesNaoEncontradosException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientesNaoEncontradosAdvice {

    @ResponseBody
    @ExceptionHandler(ClientesNaoEncontradosException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String clientesNaoEncontradosHandler(ClientesNaoEncontradosException ex) {
        return ex.getMessage();
    }
}

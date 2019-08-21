package com.cassio.player.advices;

import com.cassio.player.exceptions.ClienteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClienteNaoEncontradoAdvice {

    @ResponseBody
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clienteNaoEncontradoHandler(ClienteNaoEncontradoException ex) {
        return ex.getMessage();
    }
}

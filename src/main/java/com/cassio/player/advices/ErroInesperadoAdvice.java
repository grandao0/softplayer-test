package com.cassio.player.advices;

import com.cassio.player.exceptions.ErroInesperadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErroInesperadoAdvice {

    @ResponseBody
    @ExceptionHandler(ErroInesperadoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String erroInesperadoHandler(ErroInesperadoException ex) {
        return ex.getMessage();
    }
}

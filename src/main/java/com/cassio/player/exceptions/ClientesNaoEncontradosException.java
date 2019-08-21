package com.cassio.player.exceptions;

public class ClientesNaoEncontradosException extends RuntimeException {

    public ClientesNaoEncontradosException(String mensagem) {
        super(mensagem);
    }
}
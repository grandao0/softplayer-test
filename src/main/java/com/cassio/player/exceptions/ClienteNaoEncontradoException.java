package com.cassio.player.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(Integer id) {
        super("Cliente nao encontrado de ID: " + id);
    }
}

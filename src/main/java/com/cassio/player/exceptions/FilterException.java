package com.cassio.player.exceptions;

public class FilterException extends Exception {
    private static final long serialVersionUID = 7215336403919169790L;
    private int statusHttp;

    public FilterException(int statusHttp, String mensagem) {
        super(mensagem);
        this.statusHttp = statusHttp;
    }

    public FilterException(int statusHttp) {
        this.statusHttp = statusHttp;
    }

    public int getStatusHttp() {
        return this.statusHttp;
    }
}

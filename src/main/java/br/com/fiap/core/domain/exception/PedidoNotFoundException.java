package br.com.fiap.core.domain.exception;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public PedidoNotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

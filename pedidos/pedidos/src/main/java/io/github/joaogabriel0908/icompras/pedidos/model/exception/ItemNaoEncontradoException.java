package io.github.joaogabriel0908.icompras.pedidos.model.exception;

public class ItemNaoEncontradoException extends RuntimeException {

    public ItemNaoEncontradoException() {
    }

    public ItemNaoEncontradoException(String message) {
        super(message);
    }
    
}

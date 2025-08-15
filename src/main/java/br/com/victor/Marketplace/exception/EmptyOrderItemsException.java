package br.com.victor.Marketplace.exception;

public class EmptyOrderItemsException extends RuntimeException {
    public EmptyOrderItemsException(String message) {
        super(message);
    }
}

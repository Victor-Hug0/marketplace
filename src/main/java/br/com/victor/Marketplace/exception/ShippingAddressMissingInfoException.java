package br.com.victor.Marketplace.exception;

public class ShippingAddressMissingInfoException extends RuntimeException {
    public ShippingAddressMissingInfoException(String message) {
        super(message);
    }
}

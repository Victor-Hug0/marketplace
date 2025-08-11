package br.com.victor.Marketplace.exception;

public class CompanyRegistrationNumberAlreadyExistsException extends RuntimeException {
    public CompanyRegistrationNumberAlreadyExistsException(String message) {
        super(message);
    }
}

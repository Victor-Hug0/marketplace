package br.com.victor.Marketplace.exception;

public class CompanyNameAlredyExistsException extends RuntimeException {
    public CompanyNameAlredyExistsException(String message) {
        super(message);
    }
}

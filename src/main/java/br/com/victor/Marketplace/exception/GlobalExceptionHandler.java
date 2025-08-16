package br.com.victor.Marketplace.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e,  HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCpfAlreadyExistExeption(CpfAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsExeption(EmailAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPasswordExeption(InvalidPasswordException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyNameAlredyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCompanyNameAlredyExistsExeption(CompanyNameAlredyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CompanyRegistrationNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCompanyRegistrationNumberAlreadyExistsExeption(CompanyRegistrationNumberAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO =  new ErrorResponseDTO(HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FantasyNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleFantasyNameAlreadyExistsExeption(FantasyNameAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new  ErrorResponseDTO(HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException e, HttpServletRequest request) {
        Map<String, Object> errorResponseDTO = Map.of(
                "status", HttpStatus.CONFLICT.value(),
                "errors", e.getErrors(),
                "path", request.getRequestURI(),
                "timestamp", LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyOrderItemsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmptyOrderItemsException(EmptyOrderItemsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ShippingAddressMissingInfoException.class)
    public ResponseEntity<ErrorResponseDTO> handleShippingAddressMissingInfoException(ShippingAddressMissingInfoException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponseDTO> handleInsufficientStockException(InsufficientStockException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

package br.com.victor.Marketplace.exception;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        Integer status,
        String message,
        String path,
        LocalDateTime timestamp
) {
}

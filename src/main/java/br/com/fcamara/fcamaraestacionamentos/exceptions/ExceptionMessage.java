package br.com.fcamara.fcamaraestacionamentos.exceptions;

import java.time.LocalDateTime;

public record ExceptionMessage(
        int status,
        String message,
        LocalDateTime timestamp
) {
}

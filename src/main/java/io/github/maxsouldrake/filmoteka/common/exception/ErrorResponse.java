package io.github.maxsouldrake.filmoteka.common.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        String path,
        ErrorCode code
) {
}

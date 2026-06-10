package io.github.maxsouldrake.filmoteka.common.exception;

import java.util.List;

public record ValidationErrorResponse(
        ErrorResponse error,
        List<ValidationError> validationErrors
) {
}

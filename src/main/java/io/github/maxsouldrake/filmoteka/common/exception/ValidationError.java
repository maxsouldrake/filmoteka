package io.github.maxsouldrake.filmoteka.common.exception;

public record ValidationError(
        String field,
        String message
) {
}

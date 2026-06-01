package io.github.maxsouldrake.filmoteka.director.dto;

import jakarta.validation.constraints.NotNull;

public record DirectorRequest(
        @NotNull
        String name
) {
}

package io.github.maxsouldrake.filmoteka.actor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ActorRequest(
        @NotNull
        @Size(max = 100)
        String name
) {
}

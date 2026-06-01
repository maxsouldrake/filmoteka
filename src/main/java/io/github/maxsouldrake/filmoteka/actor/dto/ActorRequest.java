package io.github.maxsouldrake.filmoteka.actor.dto;

import jakarta.validation.constraints.NotNull;

public record ActorRequest(
        @NotNull
        String name
) {
}

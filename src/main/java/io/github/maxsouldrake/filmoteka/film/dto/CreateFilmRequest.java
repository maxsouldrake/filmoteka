package io.github.maxsouldrake.filmoteka.film.dto;

import io.github.maxsouldrake.filmoteka.actor.dto.ActorRequest;
import io.github.maxsouldrake.filmoteka.director.dto.DirectorRequest;
import io.github.maxsouldrake.filmoteka.film.Genre;
import jakarta.validation.constraints.*;

import java.util.Set;

public record CreateFilmRequest(
        @NotBlank
        String title,

        @NotNull
        @Min(1888)
        @Max(2100)
        Integer releaseYear,

        @NotBlank
        String country,

        String description,

        String posterUrl,

        @NotNull
        Set<Genre> genres,

        Set<ActorRequest> actors,

        Set<DirectorRequest> directors
) {

}

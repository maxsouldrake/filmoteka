package io.github.maxsouldrake.filmoteka.film.dto;

import io.github.maxsouldrake.filmoteka.actor.dto.ActorRequest;
import io.github.maxsouldrake.filmoteka.director.dto.DirectorRequest;
import io.github.maxsouldrake.filmoteka.film.Genre;
import jakarta.validation.constraints.Min;

import java.util.Set;

public record UpdateFilmRequest(
        String title,

        @Min(1888)
        Integer releaseYear,
        String country,
        String description,
        String posterUrl,
        Set<Genre> genres,
        Set<ActorRequest> actors,
        Set<DirectorRequest> directors
) {
}

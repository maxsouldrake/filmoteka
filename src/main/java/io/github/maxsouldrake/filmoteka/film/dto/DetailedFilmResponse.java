package io.github.maxsouldrake.filmoteka.film.dto;

import io.github.maxsouldrake.filmoteka.actor.dto.ActorResponse;
import io.github.maxsouldrake.filmoteka.director.dto.DirectorResponse;
import io.github.maxsouldrake.filmoteka.film.Genre;

import java.util.Set;

public record DetailedFilmResponse(
        Long id,
        String title,
        Integer releaseYear,
        String country,
        String description,
        String posterUrl,
        Set<Genre> genres,
        Set<ActorResponse> actors,
        Set<DirectorResponse> directors
) {
}

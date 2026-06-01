package io.github.maxsouldrake.filmoteka.film.dto;

public record FilmResponse(
        Long id,
        String title,
        Integer releaseYear,
        String posterUrl
) {
}

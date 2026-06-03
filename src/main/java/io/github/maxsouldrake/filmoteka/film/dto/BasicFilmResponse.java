package io.github.maxsouldrake.filmoteka.film.dto;

public record BasicFilmResponse(
        Long id,
        String title,
        Integer releaseYear
) {
}

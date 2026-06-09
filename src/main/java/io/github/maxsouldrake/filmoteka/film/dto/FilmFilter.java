package io.github.maxsouldrake.filmoteka.film.dto;

import io.github.maxsouldrake.filmoteka.film.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record FilmFilter(
        @Size(max = 255)
        String title,

        @Min(1888)
        @Max(2100)
        Integer releaseYearFrom,

        @Min(1888)
        @Max(2100)
        Integer releaseYearTo,

        Set<Genre> genres,

        @Size(max = 100)
        String country
) {
}

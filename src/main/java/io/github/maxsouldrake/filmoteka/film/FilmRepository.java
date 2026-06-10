package io.github.maxsouldrake.filmoteka.film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {
    boolean existsByTitleAndReleaseYear(String title, int releaseYear);
}

package io.github.maxsouldrake.filmoteka.film;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilmService {

    private final FilmRepository filmRepository;

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    @Transactional
    public Film create(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
}

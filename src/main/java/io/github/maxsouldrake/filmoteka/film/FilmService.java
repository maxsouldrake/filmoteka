package io.github.maxsouldrake.filmoteka.film;

import io.github.maxsouldrake.filmoteka.actor.ActorService;
import io.github.maxsouldrake.filmoteka.common.PageResponse;
import io.github.maxsouldrake.filmoteka.common.exception.ConflictException;
import io.github.maxsouldrake.filmoteka.common.exception.ResourceNotFoundException;
import io.github.maxsouldrake.filmoteka.director.DirectorService;
import io.github.maxsouldrake.filmoteka.film.dto.DetailedFilmResponse;
import io.github.maxsouldrake.filmoteka.film.dto.FilmFilter;
import io.github.maxsouldrake.filmoteka.film.dto.FilmRequest;
import io.github.maxsouldrake.filmoteka.film.dto.FilmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilmService {

    private final FilmRepository filmRepository;
    private final ActorService actorService;
    private final DirectorService directorService;
    private final FilmMapper filmMapper;

    public PageResponse<FilmResponse> getFilms(FilmFilter filter, Pageable pageable) {
        Specification<Film> specification = FilmSpecification.withFilters(filter);
        Page<Film> page = filmRepository.findAll(specification, pageable);
        List<FilmResponse> content = filmMapper.filmsToFilmResponses(page.getContent());

        return new PageResponse<>(
                content, page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages()
        );
    }

    public DetailedFilmResponse findById(Long id) {
        Film film = getFilmOrThrow(id);
        return filmMapper.filmToDetailedFilmResponse(film);
    }

    @Transactional
    public DetailedFilmResponse createFilm(FilmRequest request) {
        if (filmRepository.existsByTitleAndReleaseYear(request.title(), request.releaseYear())) {
            throw new ConflictException(String.format("Film with title '%s' and release year '%s' already exists",
                    request.title(), request.releaseYear()));
        }

        Film film = filmMapper.filmRequestToFilm(request);

        request.actors().stream()
                .map(actorService::findOrCreate)
                .forEach(film::addActor);
        request.directors().stream()
                .map(directorService::findOrCreate)
                .forEach(film::addDirector);

        Film saved = filmRepository.save(film);

        return filmMapper.filmToDetailedFilmResponse(saved);
    }

    @Transactional
    public DetailedFilmResponse updateFilm(Long id, FilmRequest request) {
        Film film = getFilmOrThrow(id);
        filmMapper.updateFilmRequestToFilm(request, film);

        film.getActors().clear();
        request.actors().stream()
                .map(actorService::findOrCreate)
                .forEach(film::addActor);

        film.getDirectors().clear();
        request.directors().stream()
                .map(directorService::findOrCreate)
                .forEach(film::addDirector);

        Film saved = filmRepository.save(film);

        return filmMapper.filmToDetailedFilmResponse(saved);
    }

    @Transactional
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    private Film getFilmOrThrow(Long id) {
        return filmRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Film with id " + id + " not found"));
    }
}

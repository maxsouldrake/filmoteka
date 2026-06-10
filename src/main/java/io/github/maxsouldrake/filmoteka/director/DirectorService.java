package io.github.maxsouldrake.filmoteka.director;

import io.github.maxsouldrake.filmoteka.common.exception.ResourceNotFoundException;
import io.github.maxsouldrake.filmoteka.director.dto.DetailedDirectorResponse;
import io.github.maxsouldrake.filmoteka.director.dto.DirectorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DetailedDirectorResponse findById(Long id) {
        Director director = getDirectorOrThrow(id);
        return directorMapper.directorToDetailedDirectorResponse(director);
    }

    @Transactional
    public Director findOrCreate(DirectorRequest request) {
        return directorRepository.findByName(request.name()).orElseGet(
                () -> directorRepository.save(directorMapper.directorRequestToDirector(request))
        );
    }

    @Transactional
    public DetailedDirectorResponse updateDirector(Long id, DirectorRequest request) {
        Director director = getDirectorOrThrow(id);
        directorMapper.updateDirectorRequestToDirector(request, director);

        Director saved = directorRepository.save(director);

        return directorMapper.directorToDetailedDirectorResponse(saved);
    }

    @Transactional
    public void deleteDirector(Long id) {
        Director director = getDirectorOrThrow(id);
        director.getFilms().forEach(film -> film.removeDirector(director));
        directorRepository.delete(director);
    }

    private Director getDirectorOrThrow(Long id) {
        return directorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Director with id " + id + " not found"));
    }
}

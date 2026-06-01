package io.github.maxsouldrake.filmoteka.director;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Optional<Director> findById(Long id) {
        return directorRepository.findById(id);
    }

    @Transactional
    public Director create(Director director) {
        return directorRepository.save(director);
    }

    @Transactional
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}

package io.github.maxsouldrake.filmoteka.actor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Optional<Actor> findById(Long id) {
        return actorRepository.findById(id);
    }

    @Transactional
    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional
    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }
}

package io.github.maxsouldrake.filmoteka.actor;

import io.github.maxsouldrake.filmoteka.actor.dto.ActorRequest;
import io.github.maxsouldrake.filmoteka.actor.dto.DetailedActorResponse;
import io.github.maxsouldrake.filmoteka.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public DetailedActorResponse findById(Long id) {
        Actor actor = getActorOrThrow(id);
        return actorMapper.actorToDetailedActorResponse(actor);
    }

    @Transactional
    public Actor findOrCreate(ActorRequest request) {
        return actorRepository.findByName(request.name()).orElseGet(
                () -> actorRepository.save(actorMapper.actorRequestToActor(request))
        );
    }

    @Transactional
    public DetailedActorResponse updateActor(Long id, ActorRequest request) {
        Actor actor = getActorOrThrow(id);
        actorMapper.updateActorRequestToActor(request, actor);

        Actor saved = actorRepository.save(actor);

        return actorMapper.actorToDetailedActorResponse(saved);
    }

    @Transactional
    public void deleteActor(Long id) {
        Actor actor = getActorOrThrow(id);
        actor.getFilms().forEach(film -> film.removeActor(actor));
        actorRepository.delete(actor);
    }

    private Actor getActorOrThrow(Long id) {
        return actorRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Actor with id " + id + " not found"));
    }
}

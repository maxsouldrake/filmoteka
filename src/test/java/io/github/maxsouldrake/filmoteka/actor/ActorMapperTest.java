package io.github.maxsouldrake.filmoteka.actor;

import io.github.maxsouldrake.filmoteka.actor.dto.ActorRequest;
import io.github.maxsouldrake.filmoteka.actor.dto.ActorResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;


class ActorMapperTest {
    private final ActorMapper actorMapper = Mappers.getMapper(ActorMapper.class);


    @Test
    void shouldMapActorRequestToActor() {
        ActorRequest request = new ActorRequest("test name");

        Actor actor = actorMapper.actorRequestToActor(request);

        assertThat(actor.getName()).isEqualTo("test name");
    }


    @Test
    void shouldMapActorToActorResponse() {
        Actor actor = Actor.builder().id(1L).name("test name").build();

        ActorResponse response = actorMapper.actorToActorResponse(actor);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("test name");
    }
}
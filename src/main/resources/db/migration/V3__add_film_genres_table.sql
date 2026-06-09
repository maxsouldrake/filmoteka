CREATE TABLE film_genres
(
    film_id     BIGINT          NOT NULL,
    genre       VARCHAR(100)    NOT NULL,
    CONSTRAINT fk_film_genres_film
        FOREIGN KEY (film_id) REFERENCES film(id) ON DELETE CASCADE
);

INSERT INTO film_genres (film_id, genre) SELECT id, g FROM film, unnest(genres) AS g;

ALTER TABLE film DROP COLUMN genres;


CREATE SCHEMA app
    AUTHORIZATION postgres;


CREATE TABLE app.artist
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.artist
    OWNER to postgres;



CREATE TABLE app.genre
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.genre
    OWNER to postgres;



CREATE TABLE app.vote
(
    id bigserial NOT NULL,
    create_at timestamptz NOT NULL,
	user_name character varying NOT NULL,
    artist_id bigint,
    about text NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (artist_id) REFERENCES app.artist (id)
);

ALTER TABLE IF EXISTS app.vote
    OWNER to postgres;



CREATE TABLE app.cross_vote_genre
(
    vote_id bigint,
    genre_id bigint,
    FOREIGN KEY (vote_id) REFERENCES app.vote (id),
    FOREIGN KEY (genre_id) REFERENCES app.genre (id),
    UNIQUE (vote_id, genre_id)
);

ALTER TABLE IF EXISTS app.cross_vote_genre
    OWNER to postgres;



--CREATE TABLE app.status
--(
--    id serial NOT NULL,
--    name character varying NOT NULL,
--    PRIMARY KEY (id)
--);
--
--ALTER TABLE IF EXISTS app.status
--    OWNER to postgres;



--CREATE TABLE app.event
--(
--    id bigserial,
--    name character varying NOT NULL,
--    start timestamptz NOT NULL,
--    status_id integer NOT NULL,
--    PRIMARY KEY (id),
--    FOREIGN KEY (status_id) REFERENCES app.status(id)
--);
--ALTER TABLE IF EXISTS app.event
--    OWNER to postgres;



--CREATE TABLE app.artist_archive
--(
--    id bigint NOT NULL,
--    name character varying NOT NULL,
--	event_id bigint NOT NULL,
--    PRIMARY KEY (id),
--	FOREIGN KEY (event_id) REFERENCES app.event (id)
--);
--
--ALTER TABLE IF EXISTS app.artist_archive
--    OWNER to postgres;


--CREATE TABLE app.genre_archive
--(
--    id bigint NOT NULL,
--    name character varying NOT NULL,
--	event_id bigint NOT NULL,
--    PRIMARY KEY (id),
--	FOREIGN KEY (event_id) REFERENCES app.event (id)
--);
--
--ALTER TABLE IF EXISTS app.genre_archive
--    OWNER to postgres;



--    CREATE TABLE app.vote_archive
--(
--    id bigint NOT NULL,
--    create_at timestamptz NOT NULL,
--	user_name character varying NOT NULL,
--    artist_id bigint,
--    about text NOT NULL,
--    PRIMARY KEY (id),
--    FOREIGN KEY (artist_id) REFERENCES app.artist_archive (id)
--);
--
--ALTER TABLE IF EXISTS app.vote_archive
--    OWNER to postgres;



--CREATE TABLE app.cross_vote_genre_archive
--(
--    vote_id bigint,
--    genre_id bigint,
--    FOREIGN KEY (vote_id) REFERENCES app.vote_archive (id),
--    FOREIGN KEY (genre_id) REFERENCES app.genre_archive (id),
--    UNIQUE (vote_id, genre_id)
--);
--
--ALTER TABLE IF EXISTS app.cross_vote_genre_archive
--    OWNER to postgres;









--- Database creation scrips for PostgreSQL

create table game
(
    id          SERIAL PRIMARY KEY,
    player_id   INTEGER     NOT NULL,
    grid        TEXT        NOT NULL,
    start_time  TIMESTAMP   NOT NULL,
    finish_time TIMESTAMP,
    state       VARCHAR(40) NOT NULL,
    result      VARCHAR(40),
    height      INTEGER     NOT NULL,
    width       INTEGER     NOT NULL
);

create table player
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL
);

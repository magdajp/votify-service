DROP TYPE IF EXISTS VOTE;
CREATE TYPE VOTE AS ENUM ('IN_FAVOR', 'AGAINST');

CREATE TABLE IF NOT EXISTS resolutions
(
    resolution_id UUID      NOT NULL,
    resident_id   UUID      NOT NULL,
    vote          VOTE      NOT NULL,
    timestamp     TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (resolution_id, resident_id),
    FOREIGN KEY (resolution_id) REFERENCES resolutions (id),
    FOREIGN KEY (resident_id) REFERENCES resolutions (id)
);

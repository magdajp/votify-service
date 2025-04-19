CREATE TABLE IF NOT EXISTS votes
(
    id            UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    resolution_id UUID      NOT NULL,
    resident_id   UUID      NOT NULL,
    vote          TEXT      NOT NULL,
    timestamp     TIMESTAMP NOT NULL DEFAULT now(),

    FOREIGN KEY (resolution_id) REFERENCES resolutions (id),
    FOREIGN KEY (resident_id) REFERENCES users (id)
);

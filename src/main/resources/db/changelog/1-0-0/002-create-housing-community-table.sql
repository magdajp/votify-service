CREATE TABLE IF NOT EXISTS housing_communities
(
    id        UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    name      TEXT      NOT NULL,
    location  TEXT      NOT NULL,
    owner_id  UUID      NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT now(),
    FOREIGN KEY (owner_id) REFERENCES users (id)
);

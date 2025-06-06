CREATE TABLE IF NOT EXISTS resolutions
(
    id                   UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    housing_community_id UUID      NOT NULL,
    title                TEXT      NOT NULL,
    content              TEXT      NOT NULL,
    deadline             TIMESTAMP NOT NULL,
    quorum               INT       NOT NULL,
    deleted              BOOLEAN   NOT NULL DEFAULT FALSE,
    timestamp            TIMESTAMP NOT NULL DEFAULT now(),
    FOREIGN KEY (housing_community_id) REFERENCES housing_communities (id)
);

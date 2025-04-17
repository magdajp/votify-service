CREATE TABLE IF NOT EXISTS residents
(
    user_id                UUID      NOT NULL,
    housing_communities_id UUID      NOT NULL,
    timestamp              TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id, housing_communities_id),
    FOREIGN KEY (housing_communities_id) REFERENCES users (id),
    FOREIGN KEY (housing_communities_id) REFERENCES housing_communities (id)
);

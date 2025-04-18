CREATE TABLE IF NOT EXISTS residents
(
    user_id              UUID      NOT NULL,
    housing_community_id UUID      NOT NULL,
    timestamp            TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id, housing_community_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (housing_community_id) REFERENCES housing_communities (id)
);

CREATE TABLE IF NOT EXISTS auditlog
(
    id        BIGSERIAL PRIMARY KEY,
    payload   TEXT      NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT now()
);
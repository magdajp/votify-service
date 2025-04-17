CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    mail       TEXT      NOT NULL UNIQUE,
    first_name TEXT      NOT NULL,
    last_name  TEXT      NOT NULL,
    timestamp  TIMESTAMP NOT NULL DEFAULT now()
);

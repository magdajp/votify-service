CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    email      TEXT      NOT NULL UNIQUE,
    first_name TEXT      NOT NULL,
    last_name  TEXT      NOT NULL,
    timestamp  TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO users(id, email, first_name, last_name)
VALUES ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad111', 'jan.dzban@gmail.com', 'Jan', 'Dzban'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad222', 'peter.griffin@gmail.com', 'Peter', 'Griffin'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad333', 'peter.porker@gmail.com', 'Peter', 'Porker'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad444', 'han.solo@gmail.com', 'Han', 'Solo'),
       ('c708ee2a-5b19-4cf8-ab3d-bbbc923ad555', 'eric.cartman@gmail.com', 'Eric', 'Cartman');
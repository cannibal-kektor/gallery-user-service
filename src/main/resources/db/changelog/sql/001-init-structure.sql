CREATE SCHEMA if NOT EXISTS user_info;
SET
search_path TO user_info;

CREATE SEQUENCE id_user_sequence_generator START WITH 5000 INCREMENT BY 1 CACHE 100;

CREATE TABLE users
(
    id       bigint PRIMARY KEY DEFAULT nextval('id_user_sequence_generator'),
    username VARCHAR(30)  NOT NULL UNIQUE,
    email    VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT valid_email CHECK (email ~* '^[a-z0-9._-]+@[a-z0-9.-]+\.[a-z]{2,}$'),
    CONSTRAINT valid_username CHECK (username ~* '^[a-z0-9_-]{3,30}$'),
    CONSTRAINT username_length CHECK (LENGTH(username) >= 3 AND LENGTH(username) <= 30)
);

CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);
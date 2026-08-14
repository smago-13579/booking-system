--liquibase formatted sql

--changeset smago:1
CREATE SCHEMA IF NOT EXISTS auth;

CREATE TABLE IF NOT EXISTS auth.users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS auth.authority (
    id BIGSERIAL PRIMARY KEY,
    user_role VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL REFERENCES auth.users(id)
);

CREATE TABLE shelters (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE pets (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    animal_kind VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    shelter_id BIGINT NOT NULL REFERENCES shelters(id)
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE pet_photos (
    pet_id BIGINT NOT NULL REFERENCES pets(id),
    photo_url VARCHAR(255) NOT NULL,
    PRIMARY KEY (pet_id, photo_url)
);

CREATE TABLE likes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    pet_id BIGINT NOT NULL REFERENCES pets(id),
    UNIQUE (user_id, pet_id)
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    author_id BIGINT REFERENCES users(id),
    shelter_id BIGINT REFERENCES shelters(id),
    pet_id BIGINT REFERENCES pets(id),
    CHECK (
        (author_id IS NOT NULL AND shelter_id IS NULL) OR
        (shelter_id IS NOT NULL AND author_id IS NULL)
    )
);
DROP TABLE user_favorite_pets;

CREATE TABLE likes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    pet_id BIGINT NOT NULL REFERENCES pets(id),
    UNIQUE (user_id, pet_id)
);
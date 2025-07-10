CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    account_type VARCHAR(20) NOT NULL CHECK (account_type IN ('SHELTER', 'USER', 'ADMIN')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE shelters (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    account_id BIGINT UNIQUE NOT NULL REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    account_id BIGINT UNIQUE NOT NULL REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE pets (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    animal_kind VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    shelter_id BIGINT NOT NULL REFERENCES shelters(id) ON DELETE CASCADE
);

CREATE TABLE pet_photos (
    pet_id BIGINT NOT NULL REFERENCES pets(id) ON DELETE CASCADE,
    photo_url VARCHAR(255) NOT NULL,
    PRIMARY KEY (pet_id, photo_url)
);

CREATE TABLE likes (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
    pet_id BIGINT NOT NULL REFERENCES pets(id) ON DELETE CASCADE,
    UNIQUE (account_id, pet_id)
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    account_id BIGINT NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
    pet_id BIGINT NOT NULL REFERENCES pets(id) ON DELETE CASCADE
);

CREATE INDEX idx_accounts_login ON accounts(login);
CREATE INDEX idx_accounts_type ON accounts(account_type);
CREATE INDEX idx_pets_shelter ON pets(shelter_id);
CREATE INDEX idx_comments_pet ON comments(pet_id);
CREATE INDEX idx_likes_account ON likes(account_id);
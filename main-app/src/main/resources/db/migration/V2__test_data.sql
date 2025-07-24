INSERT INTO accounts (login, password, account_type, created_at) VALUES
('shelter1@example.com', '$2a$10$IsF4x.4I49MCAcaT0EVtt.rZ1ySA5pDk/aKKaPkIs6nS0O.GF7G5G', 'SHELTER', CURRENT_TIMESTAMP),
('shelter2@example.com', '$2a$10$7GlQ5xDQ8DWPG1BzZs4rC.h5DZyqRlhENDjkNxnDt.zvCEVEYyeBG', 'SHELTER', CURRENT_TIMESTAMP),
('user1', '$2a$10$e7nzPMtCeuLBkK9dt1KZXurMNOLmBbVlzL6RNzgIHZ3i8kKl2.oae', 'USER', CURRENT_TIMESTAMP),
('user2', '$2a$10$L3N/iurcUAnps/gKlcUpHO9UsdoggQHPiByXLPd9qgExjOYq23duG', 'USER', CURRENT_TIMESTAMP),
('user3', '$2a$10$mESjr/we7mbmeKppOfnzLu5Ss2jKV4FqKlmSiUvf9B/aRCC6GobjG', 'USER', CURRENT_TIMESTAMP),
('admin@example.com', '$2a$10$ZbQDXsrWYJTHP6Q.nNrE4u5hnY4LycUXEEVjyPdDnzVJ5mysTf4Da', 'ADMIN', CURRENT_TIMESTAMP);

INSERT INTO shelters (title, city, phone_number, email, account_id) VALUES
('Happy Tails', 'Минск', '1234567890', 'shelter1@example.com', 1),
('Dog Haven', 'Гомель', '2345678901', 'shelter2@example.com', 2);

INSERT INTO users (username, account_id) VALUES
('user1', 3),
('user2', 4),
('user3', 5);

INSERT INTO pets (name, description, animal_kind, shelter_id, created_at) VALUES
('Барсик', 'Очень ласковый кот', 'CAT', 1, CURRENT_TIMESTAMP),
('Шарик', 'Весёлый пёс, любит гулять', 'DOG', 1, CURRENT_TIMESTAMP),
('Лапа', 'Маленький любопытный щенок', 'DOG', 2, CURRENT_TIMESTAMP),
('Муся', 'Спокойная взрослая кошка', 'CAT', 2, CURRENT_TIMESTAMP),
('Рыжик', 'Любит играть с детьми', 'CAT', 1, CURRENT_TIMESTAMP);

INSERT INTO pet_photos (pet_id, photo_url) VALUES
(1, 'https://example.com/images/barsik.jpg'),
(2, 'https://example.com/images/sharik.jpg'),
(3, 'https://example.com/images/lapa.jpg'),
(4, 'https://example.com/images/musya.jpg'),
(5, 'https://example.com/images/ryzhik.jpg');

INSERT INTO likes (account_id, pet_id) VALUES
(3, 1),
(3, 2),
(4, 3),
(4, 4),
(5, 1),
(5, 3);

INSERT INTO comments (content, account_id, pet_id, created_at) VALUES
('Какой милый!', 3, 1, CURRENT_TIMESTAMP),
('Очень хочу забрать домой', 3, 2, CURRENT_TIMESTAMP),
('Питомец выглядит отлично', 4, 3, CURRENT_TIMESTAMP),
('Хорошо бы поиграть с ним', 5, 1, CURRENT_TIMESTAMP),
('Спокойная и уравновешенная кошка', 4, 4, CURRENT_TIMESTAMP),
('Котик просто прелесть!', 5, 5, CURRENT_TIMESTAMP);

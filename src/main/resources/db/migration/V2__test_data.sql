INSERT INTO accounts (login, password, account_type, created_at) VALUES
('shelter1@example.com', 'pass123', 'SHELTER', CURRENT_TIMESTAMP),
('shelter2@example.com', 'pass456', 'SHELTER', CURRENT_TIMESTAMP),
('user1', 'pass789', 'USER', CURRENT_TIMESTAMP),
('user2', 'pass321', 'USER', CURRENT_TIMESTAMP),
('user3', 'pass654', 'USER', CURRENT_TIMESTAMP),
('admin@example.com', 'adminpass', 'ADMIN', CURRENT_TIMESTAMP);

INSERT INTO shelters (title, city, phone_number, email, account_id) VALUES
('Happy Tails', 'Минск', '1234567890', 'contact@happytails.by', 1),
('Dog Haven', 'Гомель', '2345678901', 'info@doghaven.by', 2);

INSERT INTO users (username, account_id) VALUES
('catlover99', 3),
('doggoFan22', 4),
('animalLover88', 5);

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

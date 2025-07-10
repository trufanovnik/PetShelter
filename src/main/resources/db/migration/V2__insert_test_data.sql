INSERT INTO shelters (title, city, phone_number, email, password)
VALUES
    ('Лапа помощи', 'Москва', '+79991234567', 'pawhelp@mail.ru', 'pass123'),
    ('Дом для хвостов', 'Санкт-Петербург', '+79997654321', 'tailshome@gmail.com', 'password'),
    ('Усы и лапы', 'Казань', '+79876543210', 'whiskers@yandex.ru', 'securepass');

INSERT INTO pets (name, description, animal_kind, shelter_id)
VALUES
    ('Барсик', 'Ласковый мейн-кун', 'CAT', 1),
    ('Шарик', 'Добрый дворовый пёс', 'DOG', 1),
    ('Мурка', 'Пушистая трехцветная кошка', 'CAT', 2),
    ('Рекс', 'Бывший служебный пёс', 'DOG', 3),
    ('Зефир', 'Белый кролик', 'OTHER', 2);

INSERT INTO users (username, password)
VALUES
    ('ivan_p', 'securepass123'),
    ('maria_s', 'mypassword456'),
    ('admin', 'adminpass');

INSERT INTO pet_photos (pet_id, photo_url)
VALUES
    (1, '/photos/barsik1.jpg'),
    (1, '/photos/barsik2.jpg'),
    (2, '/photos/sharik1.jpg'),
    (3, '/photos/murka1.jpg'),
    (4, '/photos/rex1.jpg'),
    (5, '/photos/zephyr1.jpg');

INSERT INTO likes (user_id, pet_id)
VALUES
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 4),
    (3, 5);

INSERT INTO comments (content, author_id, pet_id)
VALUES
    ('Какой красивый кот!', 1, 1),
    ('Хочу забрать этого пса!', 2, 2),
    ('Когда можно приехать посмотреть?', 1, 3);

INSERT INTO comments (content, shelter_id, pet_id)
VALUES
    ('Этот питомец уже привит', 1, 1),
    ('Собака прошла курс дрессировки', 3, 4);
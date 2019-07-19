CREATE TABLE Type (
    id SERIAL PRIMARY KEY,
    type_name VARCHAR(300)
);

CREATE TABLE Product(
id SERIAL PRIMARY KEY,
product_name VARCHAR(300),
type_id INTEGER REFERENCES Type(id),
expired_date DATE,
price MONEY
);

INSERT INTO Type(type_name) VALUES ('Сыр');
INSERT INTO Type(type_name) VALUES ('Молоко');
INSERT INTO Type(type_name) VALUES ('Мороженное');
INSERT INTO Product(product_name, type_id, expired_date, price) VALUES (
    'Rockford',
    1,
    '08.18.2019',
    '18.50'
);

--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM Product INNER JOIN Type AS t ON p.type_id = t.id
WHERE type_name = 'Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM Product WHERE product_name like '%Мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM Product AS p
WHERE extract(MONTH FROM(current_date)) + 1 = extract(MONTH FROM(p.expired_date));

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM Product WHERE price = (select MAX(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.type_name, count (p.type_id)
FROM product AS p INNER JOIN Type AS t ON p.type_id = t.id
GROUP BY t.type_name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product WHERE type_id = 1 or type_id = 2;
--or
SELECT * FROM product AS p INNER JOIN Type AS t ON p.type_id = t.id
WHERE t.type_name = 'Молоко' or t.type_name = 'Сыр';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.type_name FROM product AS p INNER JOIN Type AS t ON p.type_id = t.id
GROUP BY t.type_name
HAVING count(t.type_name) < 10;

--8. Вывести все продукты и их тип.
SELECT p.product_name, t.type_name
FROM product AS p INNER JOIN Type AS t ON p.type_id = t.id
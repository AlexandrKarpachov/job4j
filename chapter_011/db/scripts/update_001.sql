CREATE TABLE IF NOT EXISTS Users (
    id int primary key,
    login varchar(255),
    "name" varchar(255),
    email varchar(255),
    createDate text,
    UNIQUE (login)
);
CREATE SCHEMA IF NOT EXISTS expenses_schema;

SET SCHEMA expenses_schema;

CREATE TABLE IF NOT EXISTS category (
    id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS expense (
    id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    date VARCHAR(10),
    amount DOUBLE NOT NULL,
    description VARCHAR(100),
    category_id LONG NOT NULL,
    category_name VARCHAR(30) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);
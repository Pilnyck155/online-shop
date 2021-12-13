CREATE TABLE Products (
    product_id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    price DECIMAL (10,2) NOT NULL,
    date DATE NOT NULL);
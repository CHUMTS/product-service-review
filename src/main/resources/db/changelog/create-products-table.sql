CREATE TABLE products (
        id SERIAL PRIMARY KEY,
        color VARCHAR(255),
        manufacturer VARCHAR(255),
        year INT,
        type VARCHAR(255),
        model_name VARCHAR(255),
        price DECIMAL(10, 2)
        );
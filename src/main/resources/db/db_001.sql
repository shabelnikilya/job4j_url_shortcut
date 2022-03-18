CREATE TABLE IF NOT EXISTS site(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    login VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    created TIMESTAMP
);
CREATE TABLE IF NOT EXISTS url(
    id SERIAL PRIMARY KEY,
    link VARCHAR(255) UNIQUE,
    unique_code VARCHAR(100),
    statistic INT,
    site_id INT REFERENCES site(id)
);
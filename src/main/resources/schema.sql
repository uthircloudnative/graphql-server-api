CREATE TABLE IF NOT EXISTS  tb_user  (
    id VARCHAR(255) DEFAULT random_uuid() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    gender VARCHAR(1) NOT NULL,
    created_date TIMESTAMP,
    modified_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS  address  (
    id VARCHAR(255) DEFAULT random_uuid() PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    street1 VARCHAR(255) NOT NULL,
    street2 VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    zip INTEGER NOT NULL,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

CREATE TABLE IF NOT EXISTS  phone  (
    id VARCHAR(255) DEFAULT random_uuid() PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    number VARCHAR(10) NOT NULL,
    country_code VARCHAR(1) NOT NULL,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    FOREIGN KEY (id) REFERENCES tb_user(id)
);

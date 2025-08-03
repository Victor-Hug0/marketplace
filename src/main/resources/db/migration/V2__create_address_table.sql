CREATE TABLE address(
    id BIGSERIAL PRIMARY KEY,
    zip_code VARCHAR(10) NOT NULL,
    state VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    number VARCHAR(10)
)
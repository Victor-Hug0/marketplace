CREATE TABLE stores(
    id SERIAL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL UNIQUE,
    fantasy_name VARCHAR(255) NOT NULL UNIQUE,
    company_registration_number VARCHAR(20) NOT NULL UNIQUE,
    biography TEXT,
    logo_url VARCHAR(255),
    banner_url VARCHAR(255),
    contact_phone_number VARCHAR(11),
    contact_email VARCHAR(255) NOT NULL UNIQUE,
    store_status VARCHAR(20) NOT NULL DEFAULT 'UNDER_REVIEW',
    average_rating DECIMAL(2,1) NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activate_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
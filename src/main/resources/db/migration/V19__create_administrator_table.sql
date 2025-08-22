CREATE TABLE administrators(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TABLE aprove_requests(
    id BIGSERIAL PRIMARY KEY,
    entity_id VARCHAR(255) NOT NULL,
    entity_type VARCHAR(50) NOT NULL,
    request_type VARCHAR(50) NOT NULL,
    revisor_id BIGINT NOT NULL REFERENCES administrators(id),
    status VARCHAR(20) NOT NULL,
    justification TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    answered_in TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE admin_login_attempts(
    id BIGSERIAL PRIMARY KEY,
    administrator_id BIGINT NOT NULL REFERENCES administrators(id),
    ip_address VARCHAR(50) NOT NULL,
    date_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    was_successful BOOLEAN NOT NULL
);
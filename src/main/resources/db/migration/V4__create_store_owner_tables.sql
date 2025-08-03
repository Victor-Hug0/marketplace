CREATE TABLE store_owner(
                            id SERIAL PRIMARY KEY,
                            email VARCHAR(255) NOT NULL UNIQUE,
                            password VARCHAR(255) NOT NULL,
                            phone_number VARCHAR(20) NOT NULL,
                            store_owner_type VARCHAR(20) NOT NULL,
                            gender VARCHAR(20) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
                            updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE natural_person_owner(
                                     id SERIAL PRIMARY KEY,
                                     first_name VARCHAR(255) NOT NULL,
                                     last_name VARCHAR(255) NOT NULL,
                                     ssn VARCHAR(9) NOT NULL UNIQUE,
                                     birth_date DATE NOT NULL,
                                     FOREIGN KEY (id) REFERENCES store_owner(id) ON DELETE CASCADE
);

CREATE TABLE legal_person_owner(
                                   id SERIAL PRIMARY KEY,
                                   company_name VARCHAR(255) NOT NULL,
                                   fantasy_name VARCHAR(255) NOT NULL,
                                   company_registration_number VARCHAR(20) NOT NULL UNIQUE,
                                   FOREIGN KEY (id) REFERENCES store_owner(id) ON DELETE CASCADE
);
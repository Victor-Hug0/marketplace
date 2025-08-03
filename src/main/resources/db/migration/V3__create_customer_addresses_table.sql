CREATE TABLE customer_addresses(
                                   address_id BIGSERIAL NOT NULL,
                                   customer_id uuid NOT NULL,
                                   FOREIGN KEY (address_id) REFERENCES address(id),
                                   FOREIGN KEY (customer_id) REFERENCES customers(id),
                                   PRIMARY KEY (address_id, customer_id)
);
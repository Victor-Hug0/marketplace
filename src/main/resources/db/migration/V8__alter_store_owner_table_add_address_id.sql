ALTER TABLE store_owner ADD COLUMN address_id BIGINT;

ALTER TABLE store_owner ALTER COLUMN address_id SET NOT NULL;

ALTER TABLE store_owner ADD CONSTRAINT fk_address_on_store_owner FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE stores ADD COLUMN store_owner_id BIGINT;

ALTER TABLE stores ALTER COLUMN store_owner_id SET NOT NULL;

ALTER TABLE stores ADD CONSTRAINT fk_stores_on_store_owner FOREIGN KEY (store_owner_id) REFERENCES store_owner(id) ON DELETE RESTRICT;

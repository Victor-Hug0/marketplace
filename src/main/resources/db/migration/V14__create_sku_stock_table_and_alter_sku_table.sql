CREATE TABLE sku_stock(
    id BIGSERIAL PRIMARY KEY,
    sku_id BIGINT NOT NULL REFERENCES skus(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    store_id BIGINT NOT NULL REFERENCES stores(id),
    avaliable_stock INTEGER NOT NULL,
    reserved_quantity INTEGER NOT NULL DEFAULT 0,
    last_update TIMESTAMP NOT NULL DEFAULT now()
);

ALTER TABLE skus DROP COLUMN stock;

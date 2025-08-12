ALTER TABLE products ADD COLUMN published_at TIMESTAMP;

ALTER TABLE products ALTER COLUMN published_at SET NOT NULL;
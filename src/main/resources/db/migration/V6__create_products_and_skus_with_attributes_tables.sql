CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    store_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE
);

CREATE TABLE categories(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL DEFAULT false,
    parent_id BIGINT,

    FOREIGN KEY (parent_id) REFERENCES categories(id)
);

CREATE TABLE product_categories (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,

    PRIMARY KEY (product_id, category_id)
);

CREATE TABLE skus (
    id BIGSERIAL PRIMARY KEY,
    sku_code VARCHAR(100) NOT NULL UNIQUE,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL CHECK (stock >= 0) DEFAULT 0,
    product_id BIGINT NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE sku_attribute_string (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    sku_id BIGINT NOT NULL,
    value VARCHAR(255) NOT NULL,

    FOREIGN KEY (sku_id) REFERENCES skus(id) ON DELETE CASCADE,

    UNIQUE (sku_id, name)
);

CREATE TABLE sku_attribute_integer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    sku_id BIGINT NOT NULL,
    value INTEGER NOT NULL,

    FOREIGN KEY (sku_id) REFERENCES skus(id) ON DELETE CASCADE,

    UNIQUE (sku_id, name)
);

CREATE TABLE sku_attribute_decimal (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    sku_id BIGINT NOT NULL,
    value DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (sku_id) REFERENCES skus(id) ON DELETE CASCADE,

    UNIQUE (sku_id, name)
);
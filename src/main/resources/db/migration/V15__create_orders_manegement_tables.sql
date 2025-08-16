CREATE TABLE shippings(
    id BIGSERIAL PRIMARY KEY,
    shipping_address_id BIGINT REFERENCES address(id) NOT NULL,
    tracking_code VARCHAR(50),
    shipping_cost DECIMAL(10, 2) NOT NULL,
    shipping_method VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    estimated_delivery_date TIMESTAMP NOT NULL,
    delivered_date TIMESTAMP
);

CREATE TABLE orders(
    id BIGSERIAL PRIMARY KEY,
    customer_id uuid REFERENCES customers(id) NOT NULL,
    shipping_id BIGINT REFERENCES shippings(id) NOT NULL,
    total_itens_amount DECIMAL(10, 2) NOT NULL,
    tax_amount DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE order_items(
    id BIGSERIAL PRIMARY KEY,
    sku_id BIGINT REFERENCES skus(id) NOT NULL,
    order_id BIGINT REFERENCES orders(id) NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    sub_total DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_payments(
    id uuid PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    installments INTEGER NOT NULL,
    order_id BIGINT REFERENCES orders(id) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    processed_at TIMESTAMP NOT NULL DEFAULT now()
);
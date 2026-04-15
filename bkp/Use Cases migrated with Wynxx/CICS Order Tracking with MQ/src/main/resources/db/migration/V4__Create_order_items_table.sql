-- V4__Create_order_items_table.sql
-- Creates the order_items table for the Order Processing system

CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_sku VARCHAR(100) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(19, 4) NOT NULL,
    discount_percent DECIMAL(5, 2) NOT NULL DEFAULT 0.00,
    discount_amount DECIMAL(19, 4) NOT NULL DEFAULT 0.0000,
    tax_rate DECIMAL(5, 2) NOT NULL DEFAULT 0.00,
    tax_amount DECIMAL(19, 4) NOT NULL DEFAULT 0.0000,
    line_total DECIMAL(19, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Indexes for performance
CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_product_id ON order_items(product_id);
CREATE INDEX idx_order_items_product_sku ON order_items(product_sku);

-- Constraints
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_quantity_positive CHECK (quantity > 0);
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_unit_price_positive CHECK (unit_price > 0);
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_discount_percent_range CHECK (discount_percent >= 0 AND discount_percent <= 100);
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_discount_amount_non_negative CHECK (discount_amount >= 0);
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_tax_rate_range CHECK (tax_rate >= 0 AND tax_rate <= 100);
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_tax_amount_non_negative CHECK (tax_amount >= 0);
ALTER TABLE order_items ADD CONSTRAINT chk_order_items_line_total_non_negative CHECK (line_total >= 0);

-- V2__Create_products_table.sql
-- Creates the products table for the Order Processing system

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(100),
    brand VARCHAR(100),
    unit_price DECIMAL(19, 4) NOT NULL,
    cost_price DECIMAL(19, 4),
    tax_rate DECIMAL(5, 2) NOT NULL DEFAULT 0.00,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    reserved_quantity INTEGER NOT NULL DEFAULT 0,
    reorder_level INTEGER NOT NULL DEFAULT 0,
    weight_kg DECIMAL(10, 3),
    image_url VARCHAR(500),
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX idx_products_sku ON products(sku);
CREATE INDEX idx_products_status ON products(status);
CREATE INDEX idx_products_category ON products(category);
CREATE INDEX idx_products_brand ON products(brand);
CREATE INDEX idx_products_unit_price ON products(unit_price);
CREATE INDEX idx_products_stock_quantity ON products(stock_quantity);

-- Constraints
ALTER TABLE products ADD CONSTRAINT chk_products_unit_price_positive CHECK (unit_price > 0);
ALTER TABLE products ADD CONSTRAINT chk_products_cost_price_non_negative CHECK (cost_price IS NULL OR cost_price >= 0);
ALTER TABLE products ADD CONSTRAINT chk_products_tax_rate_range CHECK (tax_rate >= 0 AND tax_rate <= 100);
ALTER TABLE products ADD CONSTRAINT chk_products_stock_non_negative CHECK (stock_quantity >= 0);
ALTER TABLE products ADD CONSTRAINT chk_products_reserved_non_negative CHECK (reserved_quantity >= 0);
ALTER TABLE products ADD CONSTRAINT chk_products_reserved_lte_stock CHECK (reserved_quantity <= stock_quantity);
ALTER TABLE products ADD CONSTRAINT chk_products_reorder_non_negative CHECK (reorder_level >= 0);
ALTER TABLE products ADD CONSTRAINT chk_products_status CHECK (status IN ('ACTIVE', 'INACTIVE', 'OUT_OF_STOCK', 'DISCONTINUED', 'DRAFT'));

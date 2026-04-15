-- V2__Create_products_table.sql
-- Creates the products table for the Client Order Management system

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    short_description VARCHAR(500),
    category VARCHAR(100),
    brand VARCHAR(100),
    unit_price DECIMAL(15, 2) NOT NULL,
    cost_price DECIMAL(15, 2),
    tax_rate DECIMAL(5, 2) NOT NULL DEFAULT 0.00,
    discount_percentage DECIMAL(5, 2) NOT NULL DEFAULT 0.00,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    reserved_quantity INTEGER NOT NULL DEFAULT 0,
    minimum_stock_level INTEGER NOT NULL DEFAULT 0,
    weight_kg DECIMAL(10, 3),
    unit_of_measure VARCHAR(50),
    barcode VARCHAR(100),
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    image_url VARCHAR(500),
    notes TEXT,
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
CREATE INDEX idx_products_barcode ON products(barcode);
CREATE INDEX idx_products_created_at ON products(created_at);

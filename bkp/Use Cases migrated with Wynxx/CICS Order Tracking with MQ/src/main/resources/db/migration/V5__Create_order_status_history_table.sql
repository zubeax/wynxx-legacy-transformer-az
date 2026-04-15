-- V5__Create_order_status_history_table.sql
-- Creates the order_status_history table for audit trail of order status changes

CREATE TABLE order_status_history (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    previous_status VARCHAR(30),
    new_status VARCHAR(30) NOT NULL,
    changed_by VARCHAR(255),
    comment VARCHAR(500),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_status_history_order FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Indexes for performance
CREATE INDEX idx_order_status_history_order_id ON order_status_history(order_id);
CREATE INDEX idx_order_status_history_new_status ON order_status_history(new_status);
CREATE INDEX idx_order_status_history_created_at ON order_status_history(created_at);

-- Constraints
ALTER TABLE order_status_history ADD CONSTRAINT chk_order_status_history_previous_status
    CHECK (previous_status IS NULL OR previous_status IN ('PENDING', 'CONFIRMED', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'REFUNDED'));
ALTER TABLE order_status_history ADD CONSTRAINT chk_order_status_history_new_status
    CHECK (new_status IN ('PENDING', 'CONFIRMED', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'REFUNDED'));

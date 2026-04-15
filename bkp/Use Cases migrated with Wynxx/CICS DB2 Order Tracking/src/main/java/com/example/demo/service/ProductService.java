package com.example.demo.service;

import com.example.demo.dto.CreateProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.StockAdjustmentRequestDto;
import com.example.demo.dto.UpdateProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.enums.ProductStatus;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(CreateProductRequestDto request) {
        log.info("Creating new product with SKU: {}", request.getSku());

        if (productRepository.existsBySku(request.getSku())) {
            throw new IllegalArgumentException("A product with SKU '" + request.getSku() + "' already exists");
        }

        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setShortDescription(request.getShortDescription());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setUnitPrice(request.getUnitPrice());
        product.setCostPrice(request.getCostPrice());
        product.setTaxRate(request.getTaxRate() != null ? request.getTaxRate() : BigDecimal.ZERO);
        product.setDiscountPercentage(request.getDiscountPercentage() != null ? request.getDiscountPercentage() : BigDecimal.ZERO);
        product.setStockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : 0);
        product.setReservedQuantity(0);
        product.setMinimumStockLevel(request.getMinimumStockLevel() != null ? request.getMinimumStockLevel() : 0);
        product.setWeightKg(request.getWeightKg());
        product.setUnitOfMeasure(request.getUnitOfMeasure());
        product.setBarcode(request.getBarcode());
        product.setImageUrl(request.getImageUrl());
        product.setNotes(request.getNotes());

        // Set initial status based on stock
        if (request.getStockQuantity() != null && request.getStockQuantity() > 0) {
            product.setStatus(ProductStatus.ACTIVE);
        } else {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        }

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return convertToResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDto> getProductById(Long id) {
        log.debug("Fetching product with ID: {}", id);
        return productRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDto> getProductBySku(String sku) {
        log.debug("Fetching product with SKU: {}", sku);
        return productRepository.findBySku(sku).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        log.debug("Fetching all products, page: {}", pageable.getPageNumber());
        return productRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByStatus(ProductStatus status, Pageable pageable) {
        return productRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> searchProducts(String searchTerm, Pageable pageable) {
        log.debug("Searching products with term: {}", searchTerm);
        return productRepository.findBySearchTerm(searchTerm, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceRange(minPrice, maxPrice, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getLowStockProducts() {
        log.debug("Fetching low stock products");
        return productRepository.findLowStockProducts().stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getOutOfStockProducts() {
        log.debug("Fetching out of stock products");
        return productRepository.findOutOfStockProducts().stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, UpdateProductRequestDto request) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getShortDescription() != null) product.setShortDescription(request.getShortDescription());
        if (request.getCategory() != null) product.setCategory(request.getCategory());
        if (request.getBrand() != null) product.setBrand(request.getBrand());
        if (request.getUnitPrice() != null) product.setUnitPrice(request.getUnitPrice());
        if (request.getCostPrice() != null) product.setCostPrice(request.getCostPrice());
        if (request.getTaxRate() != null) product.setTaxRate(request.getTaxRate());
        if (request.getDiscountPercentage() != null) product.setDiscountPercentage(request.getDiscountPercentage());
        if (request.getMinimumStockLevel() != null) product.setMinimumStockLevel(request.getMinimumStockLevel());
        if (request.getWeightKg() != null) product.setWeightKg(request.getWeightKg());
        if (request.getUnitOfMeasure() != null) product.setUnitOfMeasure(request.getUnitOfMeasure());
        if (request.getBarcode() != null) product.setBarcode(request.getBarcode());
        if (request.getStatus() != null) product.setStatus(request.getStatus());
        if (request.getImageUrl() != null) product.setImageUrl(request.getImageUrl());
        if (request.getNotes() != null) product.setNotes(request.getNotes());

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return convertToResponse(updatedProduct);
    }

    @Transactional
    public ProductResponseDto adjustStock(Long id, StockAdjustmentRequestDto request) {
        log.info("Adjusting stock for product ID: {}, quantity: {}", id, request.getQuantity());

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        int newQuantity = product.getStockQuantity() + request.getQuantity();
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock adjustment would result in negative stock. Current stock: "
                    + product.getStockQuantity() + ", adjustment: " + request.getQuantity());
        }

        if (request.getQuantity() > 0) {
            product.addStock(request.getQuantity());
        } else if (request.getQuantity() < 0) {
            int deductQty = Math.abs(request.getQuantity());
            if (deductQty > product.getAvailableQuantity()) {
                throw new IllegalArgumentException("Cannot deduct " + deductQty + " units. Available quantity: "
                        + product.getAvailableQuantity());
            }
            product.deductStock(deductQty);
        }

        Product updatedProduct = productRepository.save(product);
        log.info("Stock adjusted for product ID: {}. New quantity: {}", id, updatedProduct.getStockQuantity());
        return convertToResponse(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        if (!product.getOrderItems().isEmpty()) {
            throw new IllegalStateException("Cannot delete product that has been ordered. Consider discontinuing instead.");
        }

        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    @Transactional(readOnly = true)
    public List<String> getAllBrands() {
        return productRepository.findAllBrands();
    }

    private ProductResponseDto convertToResponse(Product product) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(product.getId());
        response.setSku(product.getSku());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setShortDescription(product.getShortDescription());
        response.setCategory(product.getCategory());
        response.setBrand(product.getBrand());
        response.setUnitPrice(product.getUnitPrice());
        response.setCostPrice(product.getCostPrice());
        response.setTaxRate(product.getTaxRate());
        response.setDiscountPercentage(product.getDiscountPercentage());
        response.setEffectivePrice(product.getEffectivePrice());
        response.setPriceWithTax(product.getPriceWithTax());
        response.setStockQuantity(product.getStockQuantity());
        response.setReservedQuantity(product.getReservedQuantity());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setMinimumStockLevel(product.getMinimumStockLevel());
        response.setInStock(product.isInStock());
        response.setLowStock(product.isLowStock());
        response.setOrderable(product.isOrderable());
        response.setWeightKg(product.getWeightKg());
        response.setUnitOfMeasure(product.getUnitOfMeasure());
        response.setBarcode(product.getBarcode());
        response.setStatus(product.getStatus());
        response.setStatusDisplayName(product.getStatus() != null ? product.getStatus().getDisplayName() : null);
        response.setImageUrl(product.getImageUrl());
        response.setNotes(product.getNotes());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }
}

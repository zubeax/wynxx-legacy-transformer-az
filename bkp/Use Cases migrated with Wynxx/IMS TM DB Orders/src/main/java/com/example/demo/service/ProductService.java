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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(CreateProductRequestDto request) {
        log.info("Creating new product with SKU: {}", request.getSku());

        if (productRepository.existsBySku(request.getSku())) {
            throw new IllegalArgumentException("Product with SKU '" + request.getSku() + "' already exists");
        }

        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setUnitPrice(request.getUnitPrice());
        product.setCostPrice(request.getCostPrice());
        product.setTaxRate(request.getTaxRate() != null ? request.getTaxRate() : BigDecimal.ZERO);
        product.setStockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : 0);
        product.setReservedQuantity(0);
        product.setReorderLevel(request.getReorderLevel() != null ? request.getReorderLevel() : 0);
        product.setWeightKg(request.getWeightKg());
        product.setImageUrl(request.getImageUrl());
        product.setStatus(ProductStatus.ACTIVE);

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return convertToResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDto> getProductById(Long id) {
        log.debug("Fetching product by ID: {}", id);
        return productRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDto> getProductBySku(String sku) {
        log.debug("Fetching product by SKU: {}", sku);
        return productRepository.findBySku(sku).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        log.debug("Fetching all products with pageable: {}", pageable);
        return productRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByStatus(ProductStatus status, Pageable pageable) {
        log.debug("Fetching products by status: {}", status);
        return productRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByCategory(String category, ProductStatus status, Pageable pageable) {
        log.debug("Fetching products by category: {}, status: {}", category, status);
        if (status != null) {
            return productRepository.findByCategoryAndStatus(category, status, pageable).map(this::convertToResponse);
        }
        return productRepository.findByCategory(category, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> searchProducts(String searchTerm, Pageable pageable) {
        log.debug("Searching products with term: '{}'", searchTerm);
        return productRepository.findBySearchTerm(searchTerm, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getAvailableProducts(Pageable pageable) {
        log.debug("Fetching available products");
        return productRepository.findAvailableProducts(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        log.debug("Fetching products by price range: {} - {}", minPrice, maxPrice);
        if (minPrice == null) minPrice = BigDecimal.ZERO;
        if (maxPrice == null) maxPrice = new BigDecimal("999999999");
        return productRepository.findByPriceRange(minPrice, maxPrice, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getLowStockProducts() {
        log.debug("Fetching low stock products");
        return productRepository.findLowStockProducts().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getOutOfStockProducts() {
        log.debug("Fetching out of stock products");
        return productRepository.findOutOfStockProducts().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, UpdateProductRequestDto request) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getCategory() != null) product.setCategory(request.getCategory());
        if (request.getBrand() != null) product.setBrand(request.getBrand());
        if (request.getUnitPrice() != null) product.setUnitPrice(request.getUnitPrice());
        if (request.getCostPrice() != null) product.setCostPrice(request.getCostPrice());
        if (request.getTaxRate() != null) product.setTaxRate(request.getTaxRate());
        if (request.getReorderLevel() != null) product.setReorderLevel(request.getReorderLevel());
        if (request.getWeightKg() != null) product.setWeightKg(request.getWeightKg());
        if (request.getImageUrl() != null) product.setImageUrl(request.getImageUrl());
        if (request.getStatus() != null) product.setStatus(request.getStatus());

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return convertToResponse(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    @Transactional
    public ProductResponseDto adjustStock(Long id, StockAdjustmentRequestDto request) {
        log.info("Adjusting stock for product ID: {} by {} units. Reason: {}", id, request.getQuantity(), request.getReason());

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        product.addStock(request.getQuantity());

        Product updatedProduct = productRepository.save(product);
        log.info("Stock adjusted for product ID: {}. New stock: {}", id, updatedProduct.getStockQuantity());
        return convertToResponse(updatedProduct);
    }

    @Transactional
    public ProductResponseDto updateProductStatus(Long id, ProductStatus newStatus) {
        log.info("Updating status for product ID: {} to {}", id, newStatus);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        product.setStatus(newStatus);
        Product updatedProduct = productRepository.save(product);
        log.info("Product status updated to {} for ID: {}", newStatus, id);
        return convertToResponse(updatedProduct);
    }

    private ProductResponseDto convertToResponse(Product product) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(product.getId());
        response.setSku(product.getSku());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setBrand(product.getBrand());
        response.setUnitPrice(product.getUnitPrice());
        response.setCostPrice(product.getCostPrice());
        response.setTaxRate(product.getTaxRate());
        response.setPriceWithTax(product.getPriceWithTax());
        response.setStockQuantity(product.getStockQuantity());
        response.setReservedQuantity(product.getReservedQuantity());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setReorderLevel(product.getReorderLevel());
        response.setLowStock(product.isLowStock());
        response.setWeightKg(product.getWeightKg());
        response.setImageUrl(product.getImageUrl());
        response.setStatus(product.getStatus());
        response.setStatusDisplayName(product.getStatus() != null ? product.getStatus().getDisplayName() : null);
        response.setAvailableForSale(product.isAvailableForSale());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }
}

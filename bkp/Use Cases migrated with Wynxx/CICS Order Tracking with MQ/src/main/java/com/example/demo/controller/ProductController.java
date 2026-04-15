package com.example.demo.controller;

import com.example.demo.dto.CreateProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.StockAdjustmentRequestDto;
import com.example.demo.dto.UpdateProductRequestDto;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "APIs for managing products and inventory")
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Retrieve a paginated list of all products with optional filtering")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of products"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @Parameter(description = "Filter by product status") @RequestParam(required = false) ProductStatus status,
            @Parameter(description = "Filter by category") @RequestParam(required = false) String category,
            @Parameter(description = "Search term for name, SKU, or description") @RequestParam(required = false) String search,
            @Parameter(description = "Minimum price filter") @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price filter") @RequestParam(required = false) BigDecimal maxPrice,
            @PageableDefault(size = 20) Pageable pageable) {

        Page<ProductResponseDto> products;
        if (search != null && !search.isBlank()) {
            products = productService.searchProducts(search, pageable);
        } else if (minPrice != null || maxPrice != null) {
            products = productService.getProductsByPriceRange(minPrice, maxPrice, pageable);
        } else if (category != null && !category.isBlank()) {
            products = productService.getProductsByCategory(category, status, pageable);
        } else if (status != null) {
            products = productService.getProductsByStatus(status, pageable);
        } else {
            products = productService.getAllProducts(pageable);
        }
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get available products", description = "Retrieve products that are active and have stock available")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of available products"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/available")
    public ResponseEntity<Page<ProductResponseDto>> getAvailableProducts(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(productService.getAvailableProducts(pageable));
    }

    @Operation(summary = "Get low stock products", description = "Retrieve products that are at or below their reorder level")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of low stock products"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDto>> getLowStockProducts() {
        return ResponseEntity.ok(productService.getLowStockProducts());
    }

    @Operation(summary = "Get out of stock products", description = "Retrieve products with zero available stock")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of out of stock products"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/out-of-stock")
    public ResponseEntity<List<ProductResponseDto>> getOutOfStockProducts() {
        return ResponseEntity.ok(productService.getOutOfStockProducts());
    }

    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its unique ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of product"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get product by SKU", description = "Retrieve a specific product by its SKU")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of product"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-sku/{sku}")
    public ResponseEntity<ProductResponseDto> getProductBySku(
            @Parameter(description = "Product SKU", required = true) @PathVariable String sku) {
        return productService.getProductBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new product", description = "Add a new product to the catalog")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or SKU already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody CreateProductRequestDto request) {
        ProductResponseDto response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an existing product", description = "Update product details by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequestDto request) {
        ProductResponseDto response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a product", description = "Delete a product by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update product status", description = "Change the status of a product (ACTIVE, INACTIVE, OUT_OF_STOCK, DISCONTINUED, DRAFT)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid status value"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<ProductResponseDto> updateProductStatus(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id,
            @Parameter(description = "New product status", required = true) @RequestParam ProductStatus status) {
        ProductResponseDto response = productService.updateProductStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Adjust product stock", description = "Add stock quantity to a product (for receiving new inventory)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock adjusted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid adjustment data"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/stock/adjust")
    public ResponseEntity<ProductResponseDto> adjustStock(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id,
            @Valid @RequestBody StockAdjustmentRequestDto request) {
        ProductResponseDto response = productService.adjustStock(id, request);
        return ResponseEntity.ok(response);
    }
}

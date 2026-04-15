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
@Tag(name = "Product Management", description = "APIs for managing products in the order management system")
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Retrieve a paginated list of all products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of products"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @PageableDefault(size = 20) Pageable pageable,
            @Parameter(description = "Filter by product status") @RequestParam(required = false) ProductStatus status,
            @Parameter(description = "Filter by category") @RequestParam(required = false) String category,
            @Parameter(description = "Search term for name, SKU, description, or brand") @RequestParam(required = false) String search,
            @Parameter(description = "Minimum price filter") @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price filter") @RequestParam(required = false) BigDecimal maxPrice) {

        Page<ProductResponseDto> products;
        if (search != null && !search.isBlank()) {
            products = productService.searchProducts(search, pageable);
        } else if (minPrice != null && maxPrice != null) {
            products = productService.getProductsByPriceRange(minPrice, maxPrice, pageable);
        } else if (category != null && !category.isBlank()) {
            products = productService.getProductsByCategory(category, pageable);
        } else if (status != null) {
            products = productService.getProductsByStatus(status, pageable);
        } else {
            products = productService.getAllProducts(pageable);
        }
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its ID")
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

    @Operation(summary = "Create a new product", description = "Create a new product in the catalog")
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

    @Operation(summary = "Delete a product", description = "Delete a product by ID (only if never ordered)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Product has existing orders"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Adjust product stock", description = "Add or remove stock quantity for a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock adjusted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid adjustment or insufficient stock"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponseDto> adjustStock(
            @Parameter(description = "Product ID", required = true) @PathVariable Long id,
            @Valid @RequestBody StockAdjustmentRequestDto request) {
        ProductResponseDto response = productService.adjustStock(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get low stock products", description = "Retrieve all products at or below minimum stock level")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of low stock products"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDto>> getLowStockProducts() {
        return ResponseEntity.ok(productService.getLowStockProducts());
    }

    @Operation(summary = "Get out of stock products", description = "Retrieve all products with zero stock")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of out of stock products"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/out-of-stock")
    public ResponseEntity<List<ProductResponseDto>> getOutOfStockProducts() {
        return ResponseEntity.ok(productService.getOutOfStockProducts());
    }

    @Operation(summary = "Get all product categories", description = "Retrieve a list of all distinct product categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of categories"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(productService.getAllCategories());
    }

    @Operation(summary = "Get all product brands", description = "Retrieve a list of all distinct product brands")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of brands"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getAllBrands() {
        return ResponseEntity.ok(productService.getAllBrands());
    }
}

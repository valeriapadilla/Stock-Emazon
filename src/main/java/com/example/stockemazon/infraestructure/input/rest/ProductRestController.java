package com.example.stockemazon.infraestructure.input.rest;


import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.dto.CategoryResponse;
import com.example.stockemazon.application.dto.ProductRequest;
import com.example.stockemazon.application.dto.ProductResponse;
import com.example.stockemazon.application.handler.IProductHandler;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.infraestructure.utils.InfraestructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Validated
public class ProductRestController {
    private final IProductHandler productHandler;


    @Operation(summary = "Save a new product", description = "Creates a new product in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        productHandler.saveProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing product", description = "Updates an existing product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductRequest productRequest) {
        productHandler.updateProduct(productRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        productHandler.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Retrieve all Product with pagination and sorting include a filter of brand or categories",
            description = "This endpoint retrieves a paginated list of categories with optional sorting. You can specify the page number, page size, sort direction (ASC or DESC), the field by which to sort (productName, BrandName, categoryName) and specify the name of the brand or the category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination or sorting parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PageCustom<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = InfraestructureConstants.PAGE) Integer page,
            @RequestParam(defaultValue = InfraestructureConstants.SIZE) Integer pageSize,
            @RequestParam(defaultValue = InfraestructureConstants.SORT) String sort,
            @RequestParam(defaultValue = InfraestructureConstants.SORTBY) String sortBy,
            @RequestParam(defaultValue = InfraestructureConstants.EMPTY) String brandName,
            @RequestParam(defaultValue = InfraestructureConstants.EMPTY) String categoryName
    ){
        PageCustom<ProductResponse> products = productHandler.getAllProducts(page, pageSize, sort, sortBy, brandName,categoryName);
        return ResponseEntity.ok(products);
    }
}

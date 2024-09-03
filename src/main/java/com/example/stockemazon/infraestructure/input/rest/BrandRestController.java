package com.example.stockemazon.infraestructure.input.rest;


import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.handler.IBrandHandler;
import com.example.stockemazon.domain.model.PageCustom;
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
@RequestMapping("brand")
@RequiredArgsConstructor
@Validated
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @Operation(summary = "Save a new brand", description = "Creates a new brand in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Brand already exists")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandHandler.saveBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing brand", description = "Updates an existing brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandHandler.updateBrand(brandRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a brand", description = "Deletes a brand by its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @DeleteMapping("/{brandName}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String name) {
        brandHandler.deleteBrand(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Retrieve all brands with pagination and sorting",
            description = "This endpoint retrieves a paginated list of brands with optional sorting. You can specify the page number, page size, sort direction (ASC or DESC), and the field by which to sort (e.g., name or description).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of brands"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination or sorting parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PageCustom<BrandRequest>> getAllBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "name") String sortBy) {

        PageCustom<BrandRequest> brands = brandHandler.getAllBrands(page, size, sort, sortBy);
        return ResponseEntity.ok(brands);
    }
}

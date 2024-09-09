package com.example.stockemazon.infraestructure.input.rest;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.dto.CategoryResponse;
import com.example.stockemazon.application.handler.ICategoryHandler;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.infraestructure.utils.InfraestructureConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {
    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Save a new category", description = "Creates a new category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Category already exists")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories with pagination and sorting",
            description = "This endpoint retrieves a paginated list of categories with optional sorting. You can specify the page number, page size, sort direction (ASC or DESC), and the field by which to sort (e.g., name or description).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of categories"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination or sorting parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PageCustom<CategoryResponse>> getAllCategories(
            @RequestParam(defaultValue = InfraestructureConstants.PAGE) Integer page,
            @RequestParam(defaultValue = InfraestructureConstants.SIZE) Integer size,
            @RequestParam(defaultValue = InfraestructureConstants.SORT) String sort,
            @RequestParam(defaultValue = InfraestructureConstants.SORTBY) String sortBy) {

        PageCustom<CategoryResponse> categories = categoryHandler.getAllCategories(page, size, sort, sortBy);
        return ResponseEntity.ok(categories);
    }
}

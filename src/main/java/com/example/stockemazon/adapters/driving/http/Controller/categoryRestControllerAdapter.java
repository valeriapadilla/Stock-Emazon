package com.example.stockemazon.adapters.driving.http.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stockemazon.adapters.driving.http.dto.request.addCategoryRequest;
import com.example.stockemazon.adapters.driving.http.dto.response.categoryResponse;
import com.example.stockemazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.example.stockemazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.example.stockemazon.domain.api.ICategoryServicePort;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class categoryRestControllerAdapter {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper CategoryRequestMapper;
    private final ICategoryResponseMapper CategoryResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody addCategoryRequest request) {
        categoryServicePort.saveCategory(CategoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/search/{categoryName}")
    public ResponseEntity<categoryResponse> getCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(CategoryResponseMapper.toProductResponse(productServicePort.getProduct(productName)));
    }
}

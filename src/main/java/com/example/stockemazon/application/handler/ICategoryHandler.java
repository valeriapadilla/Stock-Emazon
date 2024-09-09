package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.dto.CategoryResponse;
import com.example.stockemazon.domain.model.PageCustom;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    PageCustom<CategoryResponse> getAllCategories(Integer page, Integer size, String sort, String sortBy);
}

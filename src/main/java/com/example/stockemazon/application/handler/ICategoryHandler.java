package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.domain.model.PageCustom;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    void updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(String categoryName);
    PageCustom<CategoryRequest> getAllCategories(int page, int size, String sort, String sortBy);
}

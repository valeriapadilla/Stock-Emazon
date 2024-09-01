package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.CategoryRequest;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    void updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(String categoryName);
}

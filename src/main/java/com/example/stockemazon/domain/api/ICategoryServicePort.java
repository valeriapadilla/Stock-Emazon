package com.example.stockemazon.domain.api;
import java.util.List;

import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(String categoryName);
    PageCustom<Category> getAllCategories(int page, int size, String sort, String sortBy);

}

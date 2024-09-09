package com.example.stockemazon.domain.api;
import java.util.List;

import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sort, String sortBy);
}

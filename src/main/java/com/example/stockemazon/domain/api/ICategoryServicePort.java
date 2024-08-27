package com.example.stockemazon.domain.api;
import java.util.List;

import com.example.stockemazon.domain.model.Category;

public interface ICategoryServicePort {
    Category getcategory(String name);
    void saveCategory(Category category);
}

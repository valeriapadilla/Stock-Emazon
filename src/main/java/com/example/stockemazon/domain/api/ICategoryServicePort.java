package com.example.stockemazon.domain.api;
import java.util.List;

import com.example.stockemazon.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}

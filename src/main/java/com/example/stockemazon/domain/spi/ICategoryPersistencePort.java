
package com.example.stockemazon.domain.spi;

import java.util.List;

import com.example.stockemazon.domain.model.Category;

public interface ICategoryPersistencePort {
    boolean existsByName(String name);
    void saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(long id);
    void updateCategory(Category category);
    void deleteCategory(long id);
}
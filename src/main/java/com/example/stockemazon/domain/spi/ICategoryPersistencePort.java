
package com.example.stockemazon.domain.spi;


import com.example.stockemazon.domain.model.Category;

public interface ICategoryPersistencePort {
    boolean findByName(String name);
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(String categoryName);
}

package com.example.stockemazon.domain.spi;


import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;

public interface ICategoryPersistencePort {
    boolean findByName(String name);
    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(int page, int size, String sort, String sortBy);
    void updateCategory(Category category);
    void deleteCategory(String categoryName);
}
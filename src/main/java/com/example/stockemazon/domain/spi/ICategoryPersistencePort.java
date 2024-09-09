
package com.example.stockemazon.domain.spi;


import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;

import java.util.List;

public interface ICategoryPersistencePort {
    boolean findByName(String name);
    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sort, String sortBy);
    boolean existsById(Long id);
}
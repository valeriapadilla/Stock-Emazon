package com.example.stockemazon.domain.api.usecases;

import java.util.List;

import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;

public class CategoryUseCases implements ICategoryServicePort{

    private ICategoryPersistencePort CategoryPersistencePort;

    public CategoryUseCases(ICategoryPersistencePort categoryPersistencePort) {
        this.CategoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (CategoryPersistencePort.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists.");
        }
        CategoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return CategoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategoryById(long id) {
        return CategoryPersistencePort.getCategoryById(id);
    }

    @Override
    public void updateCategory(Category category) {
        if(CategoryPersistencePort.existsByName(category.getName())) {
            throw new IllegalArgumentException("" +
            "Category name already exists for a different category.");
        }
        CategoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(long id) {
        CategoryPersistencePort.deleteCategory(id);
    }

}

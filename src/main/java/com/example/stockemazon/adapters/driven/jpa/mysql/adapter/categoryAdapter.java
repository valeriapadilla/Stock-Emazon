package com.example.stockemazon.adapters.driven.jpa.mysql.adapter;

import com.example.stockemazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.stockemazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class categoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper CategoryEntityMapper;

    @Override
    public boolean existsByName(String name) {
        return (categoryRepository.findByName(name).isPresent());
    }

    @Override
    public void saveCategory(Category category) {
        if (existsByName(category.getName())){
            throw new CategoryAlreadyExistsException("Category name already exists");
        }
        categoryRepository.save(CategoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(long id) {
        return null;
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(long id) {

    }
}

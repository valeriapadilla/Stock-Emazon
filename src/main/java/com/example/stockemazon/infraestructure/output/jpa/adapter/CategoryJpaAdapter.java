package com.example.stockemazon.infraestructure.output.jpa.adapter;

import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import com.example.stockemazon.infraestructure.output.jpa.mapper.ICategoryEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.ICategoryRepository;

import java.util.Optional;

public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public boolean findByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }

    @Override
    public void saveCategory(Category category) {
        if(findByName(category.getName())){
            throw new CategoryAlreadyExistsException();
        }
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void updateCategory(Category category) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findByName(category.getName());

        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            categoryEntity.setDescription(category.getDescription());
            categoryRepository.save(categoryEntity);
        }
    }

    @Override
    public void deleteCategory(String categoryName) {
        categoryRepository.deleteByName(categoryName);
    }
}

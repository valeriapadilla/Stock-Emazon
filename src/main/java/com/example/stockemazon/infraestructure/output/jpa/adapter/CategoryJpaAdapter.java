package com.example.stockemazon.infraestructure.output.jpa.adapter;

import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import com.example.stockemazon.infraestructure.output.jpa.mapper.ICategoryEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.PageMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final PageMapper pageMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper, PageMapper pageMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
        this.pageMapper = pageMapper;
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
    public PageCustom<Category> getAllCategories(int page, int size, String sort, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), sortBy);
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        return pageMapper.toPageCustomCategory(categoryEntities);
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

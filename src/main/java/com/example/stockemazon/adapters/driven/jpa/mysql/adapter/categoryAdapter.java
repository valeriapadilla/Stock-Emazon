package com.example.stockemazon.adapters.driven.jpa.mysql.adapter;

import com.example.stockemazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.example.stockemazon.adapters.driven.jpa.mysql.exceptions.ElementNotFoundException;
import com.example.stockemazon.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.example.stockemazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.stockemazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public Category getcategory(String name) {
        CategoryEntity category = categoryRepository.findByNameContaining(name).orElseThrow(ElementNotFoundException::new);
        return CategoryEntityMapper.toModel(category);

    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size) {
        Pageable pagination = PageRequest.of(page, size);
        List<CategoryEntity> categories = categoryRepository.findAll(pagination).getContent();
        if (categories.isEmpty()) {
            throw new NoDataFoundException();
        }
        return CategoryEntityMapper.toModelList(categories);
    }

    @Override
    public Category getCategoryById(long id) {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(ElementNotFoundException::new);
        return CategoryEntityMapper.toModel(category);
    }

    @Override
    public Category updateCategory(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            throw new ElementNotFoundException();
        }
        return CategoryEntityMapper.toModel(categoryRepository.save(CategoryEntityMapper.toEntity(category)));

    }

    @Override
    public void deleteCategory(long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new ElementNotFoundException();
        }
        categoryRepository.deleteById(id);
    }
}

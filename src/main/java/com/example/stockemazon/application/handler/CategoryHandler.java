package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.mapper.ICategoryRequestMapper;
import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;

    public CategoryHandler(ICategoryServicePort categoryServicePort, ICategoryRequestMapper categoryRequestMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
    }


    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(String categoryName) {
        categoryServicePort.deleteCategory(categoryName);
    }
}

package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.dto.CategoryResponse;
import com.example.stockemazon.application.mapper.ICategoryRequestMapper;
import com.example.stockemazon.application.mapper.PaginationResponseMapper;
import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final PaginationResponseMapper paginationResponseMapper;

    public CategoryHandler(ICategoryServicePort categoryServicePort, ICategoryRequestMapper categoryRequestMapper, PaginationResponseMapper paginationResponseMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
        this.paginationResponseMapper = paginationResponseMapper;
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
    public void deleteCategory(Long id) {
        categoryServicePort.deleteCategory(id);
    }

    @Override
    public PageCustom<CategoryResponse> getAllCategories(Integer page, Integer size, String sort, String sortBy) {
        PageCustom<Category> categoryPage = categoryServicePort.getAllCategories(page, size, sort, sortBy);
        return paginationResponseMapper.toPageCustomCategoryResponse(categoryPage);
    }
}

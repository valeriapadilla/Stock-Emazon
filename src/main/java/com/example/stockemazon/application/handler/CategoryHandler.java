package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.mapper.ICategoryRequestMapper;
import com.example.stockemazon.application.mapper.PaginationRequestMapper;
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
    private final PaginationRequestMapper paginationRequestMapper;

    public CategoryHandler(ICategoryServicePort categoryServicePort, ICategoryRequestMapper categoryRequestMapper, PaginationRequestMapper paginationRequestMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
        this.paginationRequestMapper = paginationRequestMapper;
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

    @Override
    public PageCustom<CategoryRequest> getAllCategories(int page, int size, String sort, String sortBy) {

        PageCustom<Category> categoryPage = categoryServicePort.getAllCategories(page, size, sort, sortBy);
        return paginationRequestMapper.toPageCustomCategoryRequest(categoryPage);
    }
}

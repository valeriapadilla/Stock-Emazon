package com.example.stockemazon.domain.usecases;


import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.exceptions.*;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.util.DomainConstant;
import com.example.stockemazon.domain.util.PaginationValidator;

public class CategoryUseCase implements ICategoryServicePort{

    private ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new MissingAttributeException(DomainConstant.CATEGORY_MISSING_ATTRIBUTE_EXCEPTION);
        }
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new MissingAttributeException(DomainConstant.CATEGORY_MISSING_ATTRIBUTE_EXCEPTION);
        }
        if(category.getName().length() > 50) {
            throw new CategoryDataOutOfLenghtException(DomainConstant.CATEGORY_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if(category.getDescription().length() > 90) {
            throw new CategoryDataOutOfLenghtException(DomainConstant.CATEGORY_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if (categoryPersistencePort.findByName(category.getName())) {
            throw new CategoryAlreadyExistsException();
        }
        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        if(!categoryPersistencePort.findByName(category.getName())){
            throw new CategoryNotFoundException(DomainConstant.CATEGORY_NOTFOUND_EXCEPTION);
        }
        this.categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(String categoryName) {
        if(!categoryPersistencePort.findByName(categoryName)){
            throw new CategoryNotFoundException(DomainConstant.CATEGORY_NOTFOUND_EXCEPTION);
        }
        this.categoryPersistencePort.deleteCategory(categoryName);
    }

    @Override
    public PageCustom<Category> getAllCategories(int page, int size, String sort, String sortBy) {
        PaginationValidator.validatePaginationParameters(page, size, sort, sortBy);
        return this.categoryPersistencePort.getAllCategories(page, size, sort, sortBy);
    }
}

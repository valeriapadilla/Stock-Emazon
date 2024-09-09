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
        if(category.getName().length() > DomainConstant.CATEGORY_MAX_CHARACTERS_NAME) {
            throw new DataOutOfLenghtException(DomainConstant.CATEGORY_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if(category.getDescription().length() > DomainConstant.CATEGORY_MAX_CHARACTERS_DESCRIPTION) {
            throw new DataOutOfLenghtException(DomainConstant.CATEGORY_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if (categoryPersistencePort.findByName(category.getName())) {
            throw new CategoryAlreadyExistsException();
        }
        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public PageCustom<Category> getAllCategories(Integer page, Integer size, String sort, String sortBy) {
        PaginationValidator.validatePaginationParameters(page, size, sort, sortBy);
        return this.categoryPersistencePort.getAllCategories(page, size, sort, sortBy);
    }
}

package com.example.stockemazon.domain.api.usecases;

import java.util.List;

import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.util.domainConstant;

public class CategoryUseCase implements ICategoryServicePort{

    private ICategoryPersistencePort CategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.CategoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (CategoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException(domainConstant.CATEGORY_ALREADY_EXIST_EXCEPTION);
        }
        CategoryPersistencePort.saveCategory(category);
    }

}

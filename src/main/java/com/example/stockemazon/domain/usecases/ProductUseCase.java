package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.api.IProductServicePort;
import com.example.stockemazon.domain.exceptions.*;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.spi.IProductPersistencePort;
import com.example.stockemazon.domain.util.DomainConstant;
import com.example.stockemazon.domain.util.PaginationValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductUseCase implements IProductServicePort {
    private IProductPersistencePort productPersistencePort;
    private ICategoryPersistencePort categoryPersistencePort;
    private IBrandPersistencePort brandPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort, ICategoryPersistencePort categoryPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }


    @Override
    public void saveProduct(Product product) {
        if(!brandPersistencePort.exitsById(product.getBrand().getId())) {
            throw new BrandNotFoundException(DomainConstant.BRAND_NOT_FOUND_EXCEPTION);
        }

        List<Category> categories = product.getCategories();

        if (categories.size() > DomainConstant.MAX_CATEGORIES_PRODUCT) {
            throw new TooManyCategoriesException(DomainConstant.TOO_MANY_CATEGORIES_EXCEPTION);
        }

        Set<Long> categoryIds = new HashSet<>();
        for (Category category : categories) {
            if (!categoryIds.add(category.getId())) {
                throw new DuplicateCategoryException(DomainConstant.DUPLICATE_CATEGORY_EXCEPTION);
            }
        }

        Set<Long> invalidCategoryIds = categories.stream()
                .map(Category::getId)
                .filter(categoryId -> !categoryPersistencePort.existsById(categoryId))
                .collect(Collectors.toSet());

        if (!invalidCategoryIds.isEmpty()) {
            throw new CategoryNotFoundException(DomainConstant.CATEGORY_NOTFOUND_EXCEPTION);
        }

        productPersistencePort.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        if(!productPersistencePort.existsById(product.getId())){
            throw new ProductNotFoundException(DomainConstant.PRODUCT_NOT_FOUND_EXCEPTION);
        }
        this.productPersistencePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productPersistencePort.existsById(id)){
            throw new ProductNotFoundException(DomainConstant.PRODUCT_NOT_FOUND_EXCEPTION);
        }
        this.productPersistencePort.deleteProduct(id);
    }

    @Override
    public PageCustom<Product> getAllProducts(Integer page, Integer pageSize, String sort, String sortBy, String brandName, String categoryName) {
        PaginationValidator.validatePaginationParameters(page,pageSize,sort,sortBy);
        if (categoryName != null && !categoryName.isBlank() && !categoryPersistencePort.findByName(categoryName)) {
            throw new CategoryNotFoundException(DomainConstant.CATEGORY_NOTFOUND_EXCEPTION + categoryName);
        }

        if (brandName != null && !brandName.isBlank() && !brandPersistencePort.findByName(brandName)) {
            throw new BrandNotFoundException(DomainConstant.BRAND_NOT_FOUND_EXCEPTION + brandName);
        }
        return productPersistencePort.getAllProducts(page,pageSize,sort,sortBy,brandName,categoryName);
    }
}

package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.api.IBrandServicePort;
import com.example.stockemazon.domain.exceptions.*;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.util.DomainConstant;
import com.example.stockemazon.domain.util.PaginationValidator;

public class BrandUseCase implements IBrandServicePort {

    private IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().isEmpty()) {
            throw new MissingAttributeException(DomainConstant.BRAND_MISSING_ATTRIBUTE_EXCEPTION);
        }
        if (brand.getDescription() == null || brand.getDescription().isEmpty()) {
            throw new MissingAttributeException(DomainConstant.BRAND_MISSING_ATTRIBUTE_EXCEPTION);
        }
        if(brand.getName().length() > DomainConstant.BRAND_MAX_CHARACTERS_NAME) {
            throw new DataOutOfLenghtException(DomainConstant.BRAND_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if(brand.getDescription().length() > DomainConstant.BRAND_MAX_CHARACTERS_DESCRIPTION) {
            throw new DataOutOfLenghtException(DomainConstant.BRAND_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if (brandPersistencePort.findByName(brand.getName())) {
            throw new BrandAlreadyExistsException();
        }
        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public PageCustom<Brand> getAllBrands(Integer page, Integer size, String sort, String sortBy) {
        PaginationValidator.validatePaginationParameters(page, size, sort, sortBy);
        return this.brandPersistencePort.getAllBrands(page, size, sort, sortBy);
    }


}

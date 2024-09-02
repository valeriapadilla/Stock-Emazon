package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.api.IBrandServicePort;
import com.example.stockemazon.domain.exceptions.*;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.util.DomainConstant;

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
        if(brand.getName().length() > 50) {
            throw new DataOutOfLenghtException(DomainConstant.BRAND_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if(brand.getDescription().length() > 120) {
            throw new DataOutOfLenghtException(DomainConstant.BRAND_DATA_OUT_OF_LENGHT_EXCEPTION);
        }
        if (brandPersistencePort.findByName(brand.getName())) {
            throw new BrandAlreadyExistsException();
        }
        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        if(!brandPersistencePort.findByName(brand.getName())){
            throw new BrandNotFoundException(DomainConstant.BRAND_NOT_FOUND_EXCEPTION);
        }
        this.brandPersistencePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(String name) {
        if(!brandPersistencePort.findByName(name)){
            throw new BrandNotFoundException(DomainConstant.BRAND_NOT_FOUND_EXCEPTION);
        }
        this.brandPersistencePort.deleteBrand(name);
    }


}

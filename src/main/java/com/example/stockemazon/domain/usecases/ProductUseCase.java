package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.spi.IProductPersistencePort;

public class ProductUseCase {
    private IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
}

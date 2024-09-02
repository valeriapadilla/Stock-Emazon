package com.example.stockemazon.domain.spi;

import com.example.stockemazon.domain.model.Brand;

public interface IBrandPersistencePort {
    boolean findByName(String name);
    void saveBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(String name);
}

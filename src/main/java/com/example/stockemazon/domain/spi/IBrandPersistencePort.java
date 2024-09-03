package com.example.stockemazon.domain.spi;

import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;

public interface IBrandPersistencePort {
    boolean findByName(String name);
    void saveBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(String name);
    PageCustom<Brand> getAllBrands(int page, int size, String sort, String sortBy);

}

package com.example.stockemazon.domain.api;

import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(Long id);
    PageCustom<Brand> getAllBrands(Integer page, Integer size, String sort, String sortBy);
}

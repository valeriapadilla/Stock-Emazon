package com.example.stockemazon.domain.api;

import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    PageCustom<Brand> getAllBrands(Integer page, Integer size, String sort, String sortBy);
}

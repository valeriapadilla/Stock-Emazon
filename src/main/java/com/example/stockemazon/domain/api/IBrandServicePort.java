package com.example.stockemazon.domain.api;

import com.example.stockemazon.domain.model.Brand;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(String name);
}

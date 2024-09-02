package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.BrandRequest;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    void updateBrand(BrandRequest brandRequest);
    void deleteBrand(String name);
}

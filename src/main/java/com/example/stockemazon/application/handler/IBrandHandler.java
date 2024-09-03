package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.domain.model.PageCustom;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    void updateBrand(BrandRequest brandRequest);
    void deleteBrand(String name);
    PageCustom<BrandRequest> getAllBrands(int page, int size, String sort, String sortBy);

}

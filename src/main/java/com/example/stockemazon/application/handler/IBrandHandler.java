package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.dto.BrandResponse;
import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.domain.model.PageCustom;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    PageCustom<BrandResponse> getAllBrands(Integer page, Integer size, String sort, String sortBy);

}

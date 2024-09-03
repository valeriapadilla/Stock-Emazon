package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.mapper.IBrandRequestMapper;
import com.example.stockemazon.application.mapper.PaginationRequestMapper;
import com.example.stockemazon.domain.api.IBrandServicePort;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BrandHandler implements IBrandHandler{
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    public BrandHandler(IBrandServicePort brandServicePort, IBrandRequestMapper brandRequestMapper, PaginationRequestMapper paginationRequestMapper) {
        this.brandServicePort = brandServicePort;
        this.brandRequestMapper = brandRequestMapper;
        this.paginationRequestMapper = paginationRequestMapper;
    }

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public void updateBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(String name) {
        brandServicePort.deleteBrand(name);
    }

    @Override
    public PageCustom<BrandRequest> getAllBrands(int page, int size, String sort, String sortBy) {
        PageCustom<Brand> brandPage = brandServicePort.getAllBrands(page, size, sort, sortBy);
        return paginationRequestMapper.toPageCustomBrandRequest(brandPage);
    }
}

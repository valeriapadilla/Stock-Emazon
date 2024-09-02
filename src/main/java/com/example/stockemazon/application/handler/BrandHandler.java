package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.mapper.IBrandRequestMapper;
import com.example.stockemazon.domain.api.IBrandServicePort;
import com.example.stockemazon.domain.model.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BrandHandler implements IBrandHandler{
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;

    public BrandHandler(IBrandServicePort brandServicePort, IBrandRequestMapper brandRequestMapper) {
        this.brandServicePort = brandServicePort;
        this.brandRequestMapper = brandRequestMapper;
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
}

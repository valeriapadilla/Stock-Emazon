package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.dto.BrandResponse;
import com.example.stockemazon.application.mapper.IBrandRequestMapper;
import com.example.stockemazon.application.mapper.PaginationResponseMapper;
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
    private final PaginationResponseMapper paginationResponseMapper;

    public BrandHandler(IBrandServicePort brandServicePort, IBrandRequestMapper brandRequestMapper, PaginationResponseMapper paginationResponseMapper) {
        this.brandServicePort = brandServicePort;
        this.brandRequestMapper = brandRequestMapper;
        this.paginationResponseMapper = paginationResponseMapper;
    }

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public PageCustom<BrandResponse> getAllBrands(Integer page, Integer size, String sort, String sortBy) {
        PageCustom<Brand> brandPage = brandServicePort.getAllBrands(page, size, sort, sortBy);
        return paginationResponseMapper.toPageCustomBrandResponse(brandPage);
    }
}

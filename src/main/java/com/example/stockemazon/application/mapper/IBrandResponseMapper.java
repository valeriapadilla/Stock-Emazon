package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.application.dto.BrandResponse;
import com.example.stockemazon.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
}

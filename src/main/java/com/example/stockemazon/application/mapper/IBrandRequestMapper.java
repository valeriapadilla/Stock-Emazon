package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.BrandRequest;
import com.example.stockemazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
    BrandRequest toBrandRequest(Brand brand);
}

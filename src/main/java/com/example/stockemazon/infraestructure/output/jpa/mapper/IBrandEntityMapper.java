package com.example.stockemazon.infraestructure.output.jpa.mapper;

import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.infraestructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
}

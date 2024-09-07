package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.ProductRequest;
import com.example.stockemazon.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductRequestMapper {
    Product toProduct (ProductRequest productRequest);
}

package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.ProductResponse;
import com.example.stockemazon.domain.model.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IProductResponseMapper {
    ProductResponse toProductResponse(Product product);
}

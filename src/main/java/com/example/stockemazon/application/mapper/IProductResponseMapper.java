package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.ProductRequest;
import com.example.stockemazon.application.dto.ProductResponse;
import com.example.stockemazon.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductResponseMapper {
    List<ProductResponse> toProductResponse(List<Product> products);
}

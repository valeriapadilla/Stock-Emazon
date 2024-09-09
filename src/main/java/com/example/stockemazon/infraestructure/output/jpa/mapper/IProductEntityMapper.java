package com.example.stockemazon.infraestructure.output.jpa.mapper;

import com.example.stockemazon.domain.model.Product;
import com.example.stockemazon.infraestructure.output.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductEntityMapper {
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "productCategories")
    ProductEntity toEntity(Product product);

    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "productCategories", target = "categories")
    Product toProduct(ProductEntity productEntity);

    List<Product> toProductsList(List<ProductEntity> productEntities);
}

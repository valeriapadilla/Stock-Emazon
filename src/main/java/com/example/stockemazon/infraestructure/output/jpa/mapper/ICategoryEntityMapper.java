package com.example.stockemazon.infraestructure.output.jpa.mapper;

import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
}

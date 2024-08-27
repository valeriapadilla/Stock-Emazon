package com.example.stockemazon.adapters.driven.jpa.mysql.mapper;

import com.example.stockemazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.example.stockemazon.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    Category toModel(CategoryEntity categoryEntity);
    CategoryEntity toEntity(Category category);
}

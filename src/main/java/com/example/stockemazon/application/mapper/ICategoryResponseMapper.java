package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.dto.CategoryResponse;
import com.example.stockemazon.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);
}

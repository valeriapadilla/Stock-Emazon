package com.example.stockemazon.adapters.driving.http.mapper;
import com.example.stockemazon.adapters.driving.http.dto.response.categoryResponse;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.stockemazon.domain.model.Category;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    categoryResponse toCategoryResponse(Category category);
}

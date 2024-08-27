package com.example.stockemazon.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.stockemazon.adapters.driving.http.dto.request.addCategoryRequest;
import com.example.stockemazon.domain.model.Category;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    @Mapping(target = "id", ignore = true)
    Category addRequestToCategory(addCategoryRequest addCategoryRequest);
}

package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
    CategoryRequest toCategoryRequest(Category category);
}

package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PaginationRequestMapper {
    private final ICategoryRequestMapper categoryRequestMapper;

    public PageCustom<CategoryRequest> toPageCustomCategoryRequest(PageCustom<Category> pageCustom) {
        List<CategoryRequest> content = pageCustom.getContent().stream()
                .map(categoryRequestMapper::toCategoryRequest)
                .collect(Collectors.toList());

        return new PageCustom<>(
                content,
                pageCustom.getTotalPages(),
                pageCustom.getTotalElements()
        );
    }
}

package com.example.stockemazon.infraestructure.output.jpa.mapper;

import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PageMapper {
    private final ICategoryEntityMapper categoryEntityMapper;

    public PageCustom<Category> toPageCustomCategory(Page<CategoryEntity> page) {
        List<Category> content = page.getContent().stream()
                .map(categoryEntityMapper::toCategory)
                .collect(Collectors.toList());

        return new PageCustom<>(
                content,
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}

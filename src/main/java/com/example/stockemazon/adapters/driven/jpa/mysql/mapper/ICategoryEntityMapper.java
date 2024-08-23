package com.example.stockemazon.adapters.driven.jpa.mysql.mapper;

import com.example.stockemazon.adapters.driven.jpa.mysql.entity.categoryEntity;
import com.example.stockemazon.domain.model.Category;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

public interface ICategoryEntityMapper {
    Category toModel(categoryEntity categoryEntity);
    categoryEntity toEntity(Category category);
    List<Category> toModelList(List<categoryEntity> categoryEntities);
}

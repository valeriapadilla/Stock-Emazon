package com.example.stockemazon.infraestructure.output.jpa.mapper;


import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.infraestructure.output.jpa.entity.BrandEntity;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {ICategoryEntityMapper.class, IBrandEntityMapper.class })
public interface PageMapper {
   ICategoryEntityMapper CATEGORY_ENTITY_MAPPER = Mappers.getMapper(ICategoryEntityMapper.class);
   IBrandEntityMapper BRAND_ENTITY_MAPPER = Mappers.getMapper(IBrandEntityMapper.class);

    default PageCustom<Category> toPageCustomCategory(Page<CategoryEntity> page) {
        PageCustom<Category> pageCustom = new PageCustom<>();

        List<Category> content = page.getContent().stream()
                .map(CATEGORY_ENTITY_MAPPER::toCategory)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNextPage(page.hasNext());
        pageCustom.setHasPreviousPage(page.hasPrevious());

        return pageCustom;
    }
    default PageCustom<Brand> toPageCustomBrand(Page<BrandEntity> page) {
        PageCustom<Brand> pageCustom = new PageCustom<>();

        List<Brand> content = page.getContent().stream()
                .map(BRAND_ENTITY_MAPPER::toBrand)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNextPage(page.hasNext());
        pageCustom.setHasPreviousPage(page.hasPrevious());

        return pageCustom;
    }
}

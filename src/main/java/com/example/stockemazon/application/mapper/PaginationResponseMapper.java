package com.example.stockemazon.application.mapper;

import com.example.stockemazon.application.dto.BrandResponse;
import com.example.stockemazon.application.dto.CategoryResponse;
import com.example.stockemazon.application.dto.ProductResponse;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ICategoryResponseMapper.class,IBrandResponseMapper.class, IProductResponseMapper.class })
public interface PaginationResponseMapper {
    ICategoryResponseMapper CATEGORY_RESPONSE_MAPPER = Mappers.getMapper(ICategoryResponseMapper.class);
    IBrandResponseMapper BRAND_RESPONSE_MAPPER = Mappers.getMapper(IBrandResponseMapper.class);
    IProductResponseMapper PRODUCT_RESPONSE_MAPPER = Mappers.getMapper(IProductResponseMapper.class);

    default PageCustom<CategoryResponse> toPageCustomCategoryResponse(PageCustom<Category> page) {
        PageCustom<CategoryResponse> pageCustom = new PageCustom<>();
        List<CategoryResponse> content = page.getContent().stream()
                .map(CATEGORY_RESPONSE_MAPPER::toCategoryResponse)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setHasNextPage(page.getHasNextPage());
        pageCustom.setHasPreviousPage(page.getHasPreviousPage());
        return pageCustom;
    }

    default PageCustom<BrandResponse> toPageCustomBrandResponse(PageCustom<Brand> page) {
        PageCustom<BrandResponse> pageCustom = new PageCustom<>();

        List<BrandResponse> content = page.getContent().stream()
                .map(BRAND_RESPONSE_MAPPER::toBrandResponse)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setHasNextPage(page.getHasNextPage());
        pageCustom.setHasPreviousPage(page.getHasPreviousPage());
        return pageCustom;
    }

    default PageCustom<ProductResponse> toPageCustomProductResponse(PageCustom<Product> page){
        PageCustom<ProductResponse> pageCustom = new PageCustom<>();

        List<ProductResponse> content = page.getContent().stream()
                .map(PRODUCT_RESPONSE_MAPPER::toProductResponse)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setHasNextPage(page.getHasNextPage());
        pageCustom.setHasPreviousPage(page.getHasPreviousPage());
        return pageCustom;
    }
}

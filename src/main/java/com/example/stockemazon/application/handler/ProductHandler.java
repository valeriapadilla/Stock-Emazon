package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.ProductRequest;
import com.example.stockemazon.application.mapper.IProductRequestMapper;
import com.example.stockemazon.domain.api.IProductServicePort;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductHandler implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;

    public ProductHandler(IProductServicePort productServicePort, IProductRequestMapper productRequestMapper) {
        this.productServicePort = productServicePort;
        this.productRequestMapper = productRequestMapper;
    }


    @Override
    public void saveProduct(ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);

        Brand brand = new Brand();
        brand.setId(productRequest.getBrandId());

        List<Long> categoryIds = productRequest.getCategoriesId();
        List<Category> categories = categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                }).toList();

        product.setCategories(categories);
        product.setBrand(brand);

        this.productServicePort.saveProduct(product);
    }

    @Override
    public void updateProduct(ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        productServicePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productServicePort.deleteProduct(id);
    }
}

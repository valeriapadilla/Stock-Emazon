package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.ProductRequest;
import com.example.stockemazon.application.dto.ProductResponse;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;

public interface IProductHandler {
    void saveProduct(ProductRequest productRequest);
    void updateProduct(ProductRequest productRequest);
    void deleteProduct(Long id);
    PageCustom<ProductResponse> getAllProducts(Integer page, Integer pagesize, String sort, String orderBy, String brandName, String categoryName);
}

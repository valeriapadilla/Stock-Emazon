package com.example.stockemazon.application.handler;

import com.example.stockemazon.application.dto.ProductRequest;

public interface IProductHandler {
    void saveProduct(ProductRequest productRequest);
    void updateProduct(ProductRequest productRequest);
    void deleteProduct(Long id);
}

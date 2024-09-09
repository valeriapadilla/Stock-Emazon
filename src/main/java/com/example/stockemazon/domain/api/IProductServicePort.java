package com.example.stockemazon.domain.api;

import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;


public interface IProductServicePort {
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    PageCustom<Product> getAllProducts(Integer page, Integer pageSize, String sort, String sortBy, String brandName, String categoryName);
}

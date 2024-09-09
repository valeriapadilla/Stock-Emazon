package com.example.stockemazon.domain.api;

import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;


public interface IProductServicePort {
    void saveProduct(Product product);
    PageCustom<Product> getAllProducts(Integer page, Integer pageSize, String sort, String sortBy, String brandName, String categoryName);
}

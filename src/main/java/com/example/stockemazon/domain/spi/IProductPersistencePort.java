package com.example.stockemazon.domain.spi;

import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;


public interface IProductPersistencePort {
    void saveProduct(Product product);
    boolean existsById(Long id);
    PageCustom<Product> getAllProducts(Integer page, Integer pageSize, String sort, String sortBy, String brandName, String categoryName);
}

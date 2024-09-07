package com.example.stockemazon.domain.api;

import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.Product;

import java.util.List;

public interface IProductServicePort {
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);

}

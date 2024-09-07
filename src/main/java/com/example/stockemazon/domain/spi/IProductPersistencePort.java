package com.example.stockemazon.domain.spi;

import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.Product;

import java.util.List;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    boolean existsById(Long id);
}

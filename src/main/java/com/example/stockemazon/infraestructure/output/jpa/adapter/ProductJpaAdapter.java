package com.example.stockemazon.infraestructure.output.jpa.adapter;

import com.example.stockemazon.domain.exceptions.BrandNotFoundException;
import com.example.stockemazon.domain.exceptions.CategoryNotFoundException;
import com.example.stockemazon.domain.model.Product;
import com.example.stockemazon.domain.spi.IProductPersistencePort;
import com.example.stockemazon.infraestructure.output.jpa.entity.BrandEntity;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import com.example.stockemazon.infraestructure.output.jpa.entity.ProductEntity;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IProductEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.IBrandRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.ICategoryRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.IProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductPersistencePort {
    private final IProductEntityMapper productEntityMapper;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;


    @Override
    public void saveProduct(Product product) {
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        Optional<BrandEntity> optionalBrandEntity= brandRepository.findById(product.getBrand().getId());

        List<CategoryEntity> categoryEntities = product.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new CategoryNotFoundException("Category with that ID not found")))
                .toList();

        if(!optionalBrandEntity.isPresent()) {
            throw new BrandNotFoundException("Brand with that ID not found");
        }
        productEntity.setBrand(optionalBrandEntity.get());
        productEntity.setProductCategories(categoryEntities);
        this.productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(Product product) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getId());
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}

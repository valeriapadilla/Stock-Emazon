package com.example.stockemazon.infraestructure.output.jpa.adapter;

import com.example.stockemazon.domain.exceptions.BrandNotFoundException;
import com.example.stockemazon.domain.exceptions.CategoryNotFoundException;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;
import com.example.stockemazon.domain.spi.IProductPersistencePort;
import com.example.stockemazon.infraestructure.output.jpa.entity.BrandEntity;
import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import com.example.stockemazon.infraestructure.output.jpa.entity.ProductEntity;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IProductEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.PageMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.IBrandRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.ICategoryRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductPersistencePort {
    private final IProductEntityMapper productEntityMapper;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final PageMapper pageMapper;


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
        productEntity.setBrandEntity(optionalBrandEntity.get());
        productEntity.setProductCategories(categoryEntities);
        this.productRepository.save(productEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public PageCustom<Product> getAllProducts(Integer page, Integer pageSize, String sort, String sortBy, String brandName, String categoryName) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(sort), sortBy);

        Specification<ProductEntity> spec = Specification.where(null);

        if (brandName != null && !brandName.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("brandEntity").get("name"), brandName));
        }

        if (categoryName != null && !categoryName.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.join("productCategories").get("name"), categoryName));
        }

        Page<ProductEntity> productEntities = productRepository.findAll(spec,pageable);
        return pageMapper.toPageCustomProduct(productEntities);
    }
}

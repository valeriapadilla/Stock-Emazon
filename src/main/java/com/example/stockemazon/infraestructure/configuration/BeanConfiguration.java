package com.example.stockemazon.infraestructure.configuration;

import com.example.stockemazon.domain.api.IBrandServicePort;
import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.api.IProductServicePort;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.spi.IProductPersistencePort;
import com.example.stockemazon.domain.usecases.BrandUseCase;
import com.example.stockemazon.domain.usecases.CategoryUseCase;
import com.example.stockemazon.domain.usecases.ProductUseCase;
import com.example.stockemazon.infraestructure.output.jpa.adapter.BrandJpaAdapter;
import com.example.stockemazon.infraestructure.output.jpa.adapter.CategoryJpaAdapter;
import com.example.stockemazon.infraestructure.output.jpa.adapter.ProductJpaAdapter;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IBrandEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.ICategoryEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IProductEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.PageMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.IBrandRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.ICategoryRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    private final PageMapper pageMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper,pageMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandEntityMapper,brandRepository,pageMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductJpaAdapter(productEntityMapper,brandRepository,categoryRepository,productRepository,pageMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort(),categoryPersistencePort(),brandPersistencePort());
    }
}

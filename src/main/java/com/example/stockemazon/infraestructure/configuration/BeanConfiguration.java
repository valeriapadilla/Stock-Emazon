package com.example.stockemazon.infraestructure.configuration;

import com.example.stockemazon.application.dto.CategoryRequest;
import com.example.stockemazon.application.mapper.ICategoryRequestMapper;
import com.example.stockemazon.application.mapper.PaginationRequestMapper;
import com.example.stockemazon.domain.api.IBrandServicePort;
import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.usecases.BrandUseCase;
import com.example.stockemazon.domain.usecases.CategoryUseCase;
import com.example.stockemazon.infraestructure.output.jpa.adapter.BrandJpaAdapter;
import com.example.stockemazon.infraestructure.output.jpa.adapter.CategoryJpaAdapter;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IBrandEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.ICategoryEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.PageMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.IBrandRepository;
import com.example.stockemazon.infraestructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRequestMapper categoryRequestMapper;

    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;


    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {

        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper,pageMapper());
    }



    @Bean
    public ICategoryServicePort categoryServicePort() {

        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public PageMapper pageMapper() {

        return new PageMapper(categoryEntityMapper);
    }

    @Bean
    public PaginationRequestMapper paginationRequestMapper() {

        return new PaginationRequestMapper(categoryRequestMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandEntityMapper,brandRepository);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
}

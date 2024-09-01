package com.example.stockemazon.infraestructure.configuration;

import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.usecases.CategoryUseCase;
import com.example.stockemazon.infraestructure.output.jpa.adapter.CategoryJpaAdapter;
import com.example.stockemazon.infraestructure.output.jpa.mapper.ICategoryEntityMapper;
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

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {

        return new CategoryUseCase(categoryPersistencePort());
    }
}

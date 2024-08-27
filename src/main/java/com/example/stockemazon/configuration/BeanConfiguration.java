package com.example.stockemazon.configuration;

import com.example.stockemazon.adapters.driven.jpa.mysql.adapter.categoryAdapter;
import com.example.stockemazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.stockemazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.stockemazon.domain.api.ICategoryServicePort;
import com.example.stockemazon.domain.api.usecases.CategoryUseCase;
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
        return new categoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}

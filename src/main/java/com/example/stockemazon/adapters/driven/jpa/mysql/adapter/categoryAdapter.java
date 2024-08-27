package com.example.stockemazon.adapters.driven.jpa.mysql.adapter;

import com.example.stockemazon.adapters.driven.jpa.mysql.exceptions.categoryAlreadyExistsException;
import com.example.stockemazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.stockemazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class categoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper CategoryEntityMapper;

    @Override
    public boolean existsByName(String name) {
        return (categoryRepository.findByName(name).isPresent());
    }

    @Override
    public void saveCategory(Category category) {
        if (existsByName(category.getName())){
            throw new categoryAlreadyExistsException();
        }
        categoryRepository.save(CategoryEntityMapper.toEntity(category));
    }

}

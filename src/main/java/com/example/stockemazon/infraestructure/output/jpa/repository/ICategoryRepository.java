package com.example.stockemazon.infraestructure.output.jpa.repository;

import com.example.stockemazon.infraestructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long>{
    Optional<CategoryEntity> findByName(String name);
}

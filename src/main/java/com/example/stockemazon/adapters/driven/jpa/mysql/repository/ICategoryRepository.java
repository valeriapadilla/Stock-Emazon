package com.example.stockemazon.adapters.driven.jpa.mysql.repository;

import com.example.stockemazon.adapters.driven.jpa.mysql.entity.categoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository  extends JpaRepository<categoryEntity, Long> {
    Optional<categoryEntity> findByNameContaining(String name);
    Optional<categoryEntity> findByName(String name);
    Page<categoryEntity> findAll(Pageable pageable);
}

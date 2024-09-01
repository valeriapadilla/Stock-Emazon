package com.example.stockemazon.infraestructure.output.jpa.entity;

import com.example.stockemazon.application.util.ApplicationConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = ApplicationConstant.CATEGORY_MAX_CHARACTERS_NAME)
    private String name;

    @Column(length = ApplicationConstant.CATEGORY_MAX_CHARACTERS_DESCRIPTION)
    private String description;
}

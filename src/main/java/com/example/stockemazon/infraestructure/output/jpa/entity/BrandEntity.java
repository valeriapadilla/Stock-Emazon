package com.example.stockemazon.infraestructure.output.jpa.entity;

import com.example.stockemazon.application.util.ApplicationConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = ApplicationConstant.BRAND_MAX_CHARACTERS_NAME)
    private String name;

    @Column(length = ApplicationConstant.BRAND_MAX_CHARACTERS_DESCRIPTION)
    private String description;
}

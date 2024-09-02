package com.example.stockemazon.infraestructure.output.jpa.adapter;

import com.example.stockemazon.domain.exceptions.BrandAlreadyExistsException;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.infraestructure.output.jpa.entity.BrandEntity;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IBrandEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.IBrandRepository;

import java.util.Optional;

public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;

    public BrandJpaAdapter(IBrandEntityMapper brandEntityMapper, IBrandRepository brandRepository) {
        this.brandEntityMapper = brandEntityMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean findByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public void saveBrand(Brand brand) {
        if(findByName(brand.getName())){
            throw new BrandAlreadyExistsException();
        }
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public void updateBrand(Brand brand) {
        Optional<BrandEntity> optionalBrandEntity = brandRepository.findByName(brand.getName());

        if (optionalBrandEntity.isPresent()) {
            BrandEntity brandEntity = optionalBrandEntity.get();
            brandEntity.setDescription(brand.getDescription());
            brandRepository.save(brandEntity);
        }
    }

    @Override
    public void deleteBrand(String name) {
        brandRepository.deleteByName(name);
    }
}

package com.example.stockemazon.infraestructure.output.jpa.adapter;

import com.example.stockemazon.domain.exceptions.BrandAlreadyExistsException;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.infraestructure.output.jpa.entity.BrandEntity;
import com.example.stockemazon.infraestructure.output.jpa.mapper.IBrandEntityMapper;
import com.example.stockemazon.infraestructure.output.jpa.mapper.PageMapper;
import com.example.stockemazon.infraestructure.output.jpa.repository.IBrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;
    private final PageMapper pageMapper;


    public BrandJpaAdapter(IBrandEntityMapper brandEntityMapper, IBrandRepository brandRepository, PageMapper pageMapper) {
        this.brandEntityMapper = brandEntityMapper;
        this.brandRepository = brandRepository;
        this.pageMapper = pageMapper;
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
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public PageCustom<Brand> getAllBrands(Integer page, Integer size, String sort, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), sortBy);
        Page<BrandEntity> brandEntities = brandRepository.findAll(pageable);
        return pageMapper.toPageCustomBrand(brandEntities);
    }

    @Override
    public boolean exitsById(Long id) {
        return brandRepository.findById(id).isPresent();
    }
}

package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.exceptions.BrandAlreadyExistsException;
import com.example.stockemazon.domain.exceptions.DataOutOfLenghtException;
import com.example.stockemazon.domain.exceptions.MissingAttributeException;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void saveBrand_ShouldThrowException_WhenBrandAlreadyExists() {
        Brand newBrand = new Brand(null, "Apple", "Technology and electronics brand");

        Mockito.when(brandPersistencePort.findByName("Apple")).thenReturn(true);

        assertThrows(BrandAlreadyExistsException.class, () ->
                brandUseCase.saveBrand(newBrand));
    }

    @Test
    void BrandUseCase_SaveBrand_WhenNameOutOfLength50Characters_ShouldThrowDataConstraintViolationException() {
        Brand invalidBrand = new Brand(null, "Brand name exceeding fifty characters to trigger an exception",
                "Innovative technology and electronics products");

        assertThrows(DataOutOfLenghtException.class, () -> {
            brandUseCase.saveBrand(invalidBrand);
        });
    }

    @Test
    void BrandUseCase_SaveBrand_WhenDescriptionOutOfLength120Characters_ShouldThrowDataConstraintViolationException() {
        Brand invalidBrand = new Brand(null,
                "Valid brand name",
                "Description of the brand that exceeds 120 characters to test that the Exception is thrown. very long, long, very long description");

        assertThrows(DataOutOfLenghtException.class, () -> {
            brandUseCase.saveBrand(invalidBrand);
        });
    }

    @Test
    void saveBrand_WhenNameIsNull_ShouldThrowMissingAttributeException() {
        Brand invalidBrand = new Brand(null, null, "A well-known electronics and technology brand");
        assertThrows(MissingAttributeException.class, () -> {
            brandUseCase.saveBrand(invalidBrand);
        });
        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

    @Test
    void saveBrand_WhenDescriptionIsNull_ShouldThrowMissingAttributeException() {
        Brand invalidBrand = new Brand(null, "Samsung", null);

        assertThrows(MissingAttributeException.class, () -> {
            brandUseCase.saveBrand(invalidBrand);
        });

        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

}
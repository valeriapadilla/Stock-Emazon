package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.exceptions.BrandAlreadyExistsException;
import com.example.stockemazon.domain.exceptions.DataOutOfLenghtException;
import com.example.stockemazon.domain.exceptions.MissingAttributeException;
import com.example.stockemazon.domain.exceptions.PaginationException;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

        when(brandPersistencePort.findByName("Apple")).thenReturn(true);

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

    @Test
    void getAllBrands_ShouldReturnPageCustom_WhenParametersAreValid() {
        int page = 0;
        int size = 10;
        String sort = "ASC";
        String sortBy = "name";

        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Sony", "Electronics and entertainment"),
                new Brand(2L, "Penguin", "Book publishing")
        );

        PageCustom<Brand> expectedPageCustom = new PageCustom<>();
        expectedPageCustom.setContent(brands);
        expectedPageCustom.setTotalElements(2L);
        expectedPageCustom.setTotalPages(1);

        when(brandPersistencePort.getAllBrands(page, size, sort, sortBy))
                .thenReturn(expectedPageCustom);

        PageCustom<Brand> result = brandUseCase.getAllBrands(page, size, sort, sortBy);

        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.getContent().size(), "The number of brands should be 2");
        assertEquals("Sony", result.getContent().get(0).getName(), "The first brand's name should be Sony");
        assertEquals("Penguin", result.getContent().get(1).getName(), "The second brand's name should be Penguin");

        verify(brandPersistencePort).getAllBrands(page, size, sort, sortBy);
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenInvalidParameters() {
        int invalidPage = -1;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        assertThrows(PaginationException.class, () -> {
            brandUseCase.getAllBrands(invalidPage, size, sortDirection, sortBy);
        });
    }

}
package com.example.stockemazon.domain.api.usecases;

import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.exceptions.CategoryDataOutOfLenghtException;
import com.example.stockemazon.domain.exceptions.MissingAttributeException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.usecases.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        Category newCategory = new Category(null, "Electrónica", "Todo lo relacionado con electrónica");

        Mockito.when(categoryPersistencePort.findByName("Electrónica")).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () ->
                categoryUseCase.saveCategory(newCategory));
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenNameOutOfLenght50Characters_ShouldThrowDataConstraintViolationException() {
        Category invalidCategory = new Category(null,"Nombre de la categoría muy largo para probar que la Excepción DataConstraintViolationException sea lanzada",
                "Todo lo relacionado a computadores");

        assertThrows(CategoryDataOutOfLenghtException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenDescriptionOutOfLenght90Characters_ShouldThrowDataConstraintViolationException() {
        Category invalidCategory = new Category(null,
                "Nombre de la categoría válido",
                "Descripción de la categoría que excede los noventa caracteres para probar que la Excepción DataConstraintViolationException sea lanzada. Esto es una descripción muy larga para realizar los test.");

        assertThrows(CategoryDataOutOfLenghtException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void saveCategory_WhenNameIsNull_ShouldThrowMissingAttributeException() {
        Category invalidCategory = new Category(null, null, "Todo lo relacionado a computadores");
        assertThrows(MissingAttributeException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
        verify(categoryPersistencePort,never()).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenDescriptionIsNull_ShouldThrowMissingAttributeException() {
        Category invalidCategory = new Category(null, "Computadores", null);

        assertThrows(MissingAttributeException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });

        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

}
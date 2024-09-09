package com.example.stockemazon.domain.api.usecases;

import com.example.stockemazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.stockemazon.domain.exceptions.DataOutOfLenghtException;
import com.example.stockemazon.domain.exceptions.MissingAttributeException;
import com.example.stockemazon.domain.exceptions.PaginationException;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.usecases.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)
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
        Category newCategory = new Category(null, "Electronics", "Everything related to electronics");

        Mockito.when(categoryPersistencePort.findByName("Electronics")).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () ->
                categoryUseCase.saveCategory(newCategory));
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenNameOutOfLenght50Characters_ShouldThrowDataConstraintViolationException() {
        Category invalidCategory = new Category(null,"Category name too long to test for DataConstraintViolationException to be thrown",
                "Everything related to electronics");

        assertThrows(DataOutOfLenghtException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenDescriptionOutOfLenght90Characters_ShouldThrowDataConstraintViolationException() {
        Category invalidCategory = new Category(null,
                "Valid category name",
                "Description of the category that exceeds 90 characters to test that the Exception is thrown. very long, long, very long description");

        assertThrows(DataOutOfLenghtException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void saveCategory_WhenNameIsNull_ShouldThrowMissingAttributeException() {
        Category invalidCategory = new Category(null, null, "Everything related to electronics");
        assertThrows(MissingAttributeException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
        verify(categoryPersistencePort,never()).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenDescriptionIsNull_ShouldThrowMissingAttributeException() {
        Category invalidCategory = new Category(null, "electronics", null);

        assertThrows(MissingAttributeException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });

        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }


    @Test
    void getAllCategories_ShouldThrowException_WhenInvalidParameters() {
        int invalidPage = -1;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        assertThrows(PaginationException.class, () -> {
            categoryUseCase.getAllCategories(invalidPage, size, sortDirection, sortBy);
        });
    }


}
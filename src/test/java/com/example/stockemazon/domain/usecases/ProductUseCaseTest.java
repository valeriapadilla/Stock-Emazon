package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.exceptions.BrandNotFoundException;
import com.example.stockemazon.domain.exceptions.CategoryNotFoundException;
import com.example.stockemazon.domain.exceptions.DuplicateCategoryException;
import com.example.stockemazon.domain.exceptions.TooManyCategoriesException;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.Product;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.spi.IProductPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductUseCaseTest {
    @InjectMocks
    private ProductUseCase productUseCase;

    @Mock
    private IProductPersistencePort productPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    private Product product;
    private Brand brand;
    private Category category1;
    private Category category2;
    private Category category3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brand = new Brand(1L, "BrandName", "Description");
        category1 = new Category(1L, "Category1", "Description1");
        category2 = new Category(2L, "Category2", "Description2");
        category3 = new Category(3L, "Category3", "Description3");

        List<Category> categories = Arrays.asList(category1, category2, category3);

        product = new Product(1L, "ProductName", "Description", 10, new BigDecimal("100.00"), brand, categories);
    }

    @Test
    void saveProduct_ShouldSaveSuccessfully_WhenValidProduct() {

        when(brandPersistencePort.exitsById(brand.getId())).thenReturn(true);
        when(categoryPersistencePort.existsById(category1.getId())).thenReturn(true);
        when(categoryPersistencePort.existsById(category2.getId())).thenReturn(true);
        when(categoryPersistencePort.existsById(category3.getId())).thenReturn(true);


        productUseCase.saveProduct(product);

        verify(productPersistencePort).saveProduct(product);
    }

    @Test
    void saveProduct_ShouldThrowBrandNotFoundException_WhenBrandDoesNotExist() {
        when(brandPersistencePort.exitsById(brand.getId())).thenReturn(false);

        assertThrows(BrandNotFoundException.class, () -> productUseCase.saveProduct(product));

        verify(productPersistencePort, never()).saveProduct(any(Product.class));
    }

    @Test
    void saveProduct_ShouldThrowTooManyCategoriesException_WhenMoreThanThreeCategories() {
        List<Category> tooManyCategories = Arrays.asList(category1, category2, category3, new Category(4L, "Category4", "Description4"));
        product.setCategories(tooManyCategories);

        when(brandPersistencePort.exitsById(brand.getId())).thenReturn(true);

        assertThrows(TooManyCategoriesException.class, () -> productUseCase.saveProduct(product));

        verify(productPersistencePort, never()).saveProduct(any(Product.class));
    }

    @Test
    void saveProduct_ShouldThrowDuplicateCategoryException_WhenDuplicateCategoriesExist() {
        List<Category> duplicateCategories = Arrays.asList(category1, category1, category2);
        product.setCategories(duplicateCategories);

        when(brandPersistencePort.exitsById(brand.getId())).thenReturn(true);

        assertThrows(DuplicateCategoryException.class, () -> productUseCase.saveProduct(product));

        verify(productPersistencePort, never()).saveProduct(any(Product.class));
    }

    @Test
    void saveProduct_ShouldThrowCategoryNotFoundException_WhenInvalidCategoryIds() {
        when(brandPersistencePort.exitsById(brand.getId())).thenReturn(true);
        when(categoryPersistencePort.existsById(category1.getId())).thenReturn(true);
        when(categoryPersistencePort.existsById(category2.getId())).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> productUseCase.saveProduct(product));

        verify(productPersistencePort, never()).saveProduct(any(Product.class));
    }

}
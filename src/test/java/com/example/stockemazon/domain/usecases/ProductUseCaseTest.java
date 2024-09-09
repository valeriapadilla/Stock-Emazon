package com.example.stockemazon.domain.usecases;

import com.example.stockemazon.domain.exceptions.BrandNotFoundException;
import com.example.stockemazon.domain.exceptions.CategoryNotFoundException;
import com.example.stockemazon.domain.exceptions.DuplicateCategoryException;
import com.example.stockemazon.domain.exceptions.TooManyCategoriesException;
import com.example.stockemazon.domain.model.Brand;
import com.example.stockemazon.domain.model.Category;
import com.example.stockemazon.domain.model.PageCustom;
import com.example.stockemazon.domain.model.Product;
import com.example.stockemazon.domain.spi.IBrandPersistencePort;
import com.example.stockemazon.domain.spi.ICategoryPersistencePort;
import com.example.stockemazon.domain.spi.IProductPersistencePort;
import com.example.stockemazon.domain.util.DomainConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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


    @Test
    void testGetAllProducts_ValidParameters_ReturnsProducts() {
        int page = 0;
        int pageSize = 10;
        String sort = "ASC";
        String sortBy = "name";
        String brandName = "BrandA";
        String categoryName = "CategoryA";

        Product product = new Product();
        Page<Product> productPage = new PageImpl<>(List.of(product));
        PageCustom<Product> pageCustom = new PageCustom<>();
        pageCustom.setContent(productPage.getContent());
        pageCustom.setTotalPages(productPage.getTotalPages());
        pageCustom.setTotalElements(productPage.getTotalElements());
        pageCustom.setHasNextPage(productPage.hasNext());
        pageCustom.setHasPreviousPage(productPage.hasPrevious());

        when(categoryPersistencePort.findByName(categoryName)).thenReturn(true);
        when(brandPersistencePort.findByName(brandName)).thenReturn(true);
        when(productPersistencePort.getAllProducts(page, pageSize, sort, sortBy, brandName, categoryName)).thenReturn(pageCustom);

        PageCustom<Product> result = productUseCase.getAllProducts(page, pageSize, sort, sortBy, brandName, categoryName);

        assertNotNull(result);
        assertEquals(pageCustom.getContent().size(), result.getContent().size());
        verify(categoryPersistencePort).findByName(categoryName);
        verify(brandPersistencePort).findByName(brandName);
        verify(productPersistencePort).getAllProducts(page, pageSize, sort, sortBy, brandName, categoryName);
    }

    @Test
    void testGetAllProducts_CategoryNotFound_ThrowsException() {
        int page = 0;
        int pageSize = 10;
        String sort = "ASC";
        String sortBy = "name";
        String brandName = "BrandA";
        String categoryName = "InvalidCategory";

        when(categoryPersistencePort.findByName(categoryName)).thenReturn(false);

        CategoryNotFoundException thrown = assertThrows(
                CategoryNotFoundException.class,
                () -> productUseCase.getAllProducts(page, pageSize, sort, sortBy, brandName, categoryName)
        );
        assertEquals(DomainConstant.CATEGORY_NOTFOUND_EXCEPTION + categoryName, thrown.getMessage());
        verify(categoryPersistencePort).findByName(categoryName);
        verifyNoInteractions(brandPersistencePort);
        verifyNoInteractions(productPersistencePort);
    }

    @Test
    void testGetAllProducts_BrandNotFound_ThrowsException() {
        int page = 0;
        int pageSize = 10;
        String sort = "ASC";
        String sortBy = "name";
        String brandName = "InvalidBrand";
        String categoryName = "CategoryA";

        when(categoryPersistencePort.findByName(categoryName)).thenReturn(true);
        when(brandPersistencePort.findByName(brandName)).thenReturn(false);

        BrandNotFoundException thrown = assertThrows(
                BrandNotFoundException.class,
                () -> productUseCase.getAllProducts(page, pageSize, sort, sortBy, brandName, categoryName)
        );
        assertEquals(DomainConstant.BRAND_NOT_FOUND_EXCEPTION + brandName, thrown.getMessage());
        verify(categoryPersistencePort).findByName(categoryName);
        verify(brandPersistencePort).findByName(brandName);
        verifyNoInteractions(productPersistencePort);
    }
}
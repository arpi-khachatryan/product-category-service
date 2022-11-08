package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.dto.CreateProductDto;
import am.itspace.productcategoryservice.dto.EditProductDto;
import am.itspace.productcategoryservice.mapper.ProductMapper;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.repository.CategoryRepository;
import am.itspace.productcategoryservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        productServiceImpl = new ProductServiceImpl(productRepository, productMapper, restTemplate);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void getAll() {
        productServiceImpl.getAll();
        verify(productRepository).findAll();
    }

    @Test
    void canSave() {
        String name = "Shoe";
        Category category = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(category);
        String title = "Sneakers";
        double price = 200;
        CreateProductDto createProductDto = CreateProductDto.builder()
                .title(title)
                .price(price)
                .category(category)
                .build();
        productServiceImpl.save(createProductDto);
        Product product1 = productMapper.mapToEntity(createProductDto);

        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());
        Product value = productArgumentCaptor.getValue();
        assertEquals(product1, value);
    }

    @Test
    void canSaveFail() {
        Product product = Product.builder()
                .id(1)
                .title("Sneakers")
                .price(200.0)
                .category(new Category(1, "shoe"))
                .build();
        productRepository.save(null);
        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product value = productArgumentCaptor.getValue();
        assertNotEquals(product, value);
    }

    @Test
    void getById() {
        Product product = Product.builder()
                .category(new Category())
                .id(1)
                .title("aa")
                .price(111)
                .build();
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));
        assert product != null;
        productServiceImpl.getById(product.getId());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void update() {
        Product product = Product.builder()
                .id(1)
                .title("Sneakers")
                .price(200.0)
                .category(new Category(1, "shoe"))
                .build();
        EditProductDto editProductDto = EditProductDto.builder()
                .title("Sneakers")
                .price(200.0)
                .category(new Category(1, "shoe"))
                .build();
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));
        verify(productRepository, times(0)).findById(1);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        productServiceImpl.update(product.getId(), editProductDto);
        product.setPrice(editProductDto.getPrice());
        productRepository.save(product);
        verify(productRepository, times(2)).save(productArgumentCaptor.capture());
        Product value = productArgumentCaptor.getValue();
        assertEquals(value, product);
    }

    @Test
    void delete() {
        Product product = Product.builder()
                .id(1)
                .title("Sneakers")
                .price(200)
                .category(new Category(1, "shoe"))
                .build();
        when(productRepository.existsById(anyInt())).thenReturn(true);
        productRepository.existsById(product.getId());
        productRepository.deleteById(product.getId());
        Optional<Object> empty = Optional.empty();
        assertEquals(empty, productRepository.findById(product.getId()));

 //        Product product = Product.builder()
//                .title("Sneakers")
//                .price(200)
//                .category(new Category(1, "shoe"))
//                .build();
//        productRepository.save(product);
//        productServiceImpl.delete(product.getId());
//        verify(productRepository).deleteById(product.getId());
//        boolean isTrue = true;
//        when(productRepository.deleteById(any())).thenReturn(Optional.ofNullable(product));
//        productServiceImpl.delete(product.getId());
//        verify(productRepository, times(1)).deleteById(any());
    }

    @Test
    void getByCategory() {
        Category category = Category.builder()
                .name("Shoe")
                .build();
        categoryRepository.save(category);
        Product product = Product.builder()
                .title("Sneakers")
                .price(200)
                .category(category)
                .build();
        productRepository.save(product);
        productServiceImpl.getByCategory(product.getCategory().getId());
        verify(productRepository).findAllByCategoryId(product.getCategory().getId());
    }
}
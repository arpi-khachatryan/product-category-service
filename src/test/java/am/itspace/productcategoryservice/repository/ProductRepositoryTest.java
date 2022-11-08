package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void existsByTitleAndPriceAndCategory() {
        String name = "Shoe";
        Category category = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(category);
        String title = "Sneakers";
        double price = 200;
        Product product = Product.builder()
                .title(title)
                .price(price)
                .category(category)
                .build();
        productRepository.save(product);
        boolean expected = productRepository.existsByTitleAndPriceAndCategory(title, price, category);
        assertTrue(expected);
    }

    @Test
    void existsByTitleAndPriceAndCategoryDoesNotExist() {
        String name = "Shoe";
        Category category = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(category);
        String title = "Sneakers";
        double price = 200;
        boolean expected = productRepository.existsByTitleAndPriceAndCategory(title, price, category);
        ;
        assertFalse(expected);
    }

    @Test
    void findAllByCategoryId() {
        String name = "Shoe";
        Category category = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(category);
        String title = "Sneakers";
        double price = 200;
        Product product = Product.builder()
                .title(title)
                .price(price)
                .category(category)
                .build();
        productRepository.save(product);
        List<Product> allByCategoryId = productRepository.findAllByCategoryId(product.getCategory().getId());
        assertNotNull(allByCategoryId);
    }
}
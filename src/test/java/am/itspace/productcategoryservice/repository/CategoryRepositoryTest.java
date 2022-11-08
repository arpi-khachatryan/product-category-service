package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void existsByName() {
        String name = "Shoe";
        Category category = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(category);
        boolean expected = categoryRepository.existsByName(name);
        assertTrue(expected);
    }

    @Test
    void existsByNameDoesNotExist() {
        String name = "Shoe";
        boolean expected = categoryRepository.existsByName(name);
        assertFalse(expected);
    }
}
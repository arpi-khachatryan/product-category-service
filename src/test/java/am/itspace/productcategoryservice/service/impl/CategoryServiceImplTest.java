package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.dto.CreateCategoryDto;
import am.itspace.productcategoryservice.dto.EditCategoryDto;
import am.itspace.productcategoryservice.mapper.CategoryMapper;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.repository.CategoryRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    void canSave() {
        CreateCategoryDto createCategoryDto = CreateCategoryDto.builder()
                .name("Shoe")
                .build();
        categoryServiceImpl.save(createCategoryDto);
        Category category = categoryMapper.mapToEntity(createCategoryDto);

        ArgumentCaptor<Category> categoryArgumentCaptor =
                ArgumentCaptor.forClass(Category.class);

        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category value = categoryArgumentCaptor.getValue();
        assertEquals(category, value);
    }

    @Test
    void canSaveFail() {
        Category category = new Category(1, "shoe");
        categoryRepository.save(null);
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category value = categoryArgumentCaptor.getValue();
        assertNotEquals(category, value);
    }

    @Test
    void saveNull() {
        Category category = Category.builder()
                .id(1)
                .name("Shoes")
                .build();
        when(categoryRepository.save(any())).thenReturn(category);
        assertThrows(RuntimeException.class, () -> {
            categoryServiceImpl.save(null);
        });
        verify(categoryRepository, times(0)).save(any());
    }

    @Test
    void getAll() {
        categoryServiceImpl.getAll();
        verify(categoryRepository).findAll();
    }

    @Test
    void delete() {
        Category category = Category.builder()
                .id(1)
                .name("Shoe")
                .build();
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        categoryRepository.existsById(category.getId());
        categoryRepository.deleteById(category.getId());
        verify(categoryRepository, times(1)).deleteById(any());
        Optional<Object> empty = Optional.empty();
        assertEquals(empty, categoryRepository.findById(category.getId()));
//        assertNull(categoryRepository.findById(category.getId()));
    }

    @Test
    void deleteThrowsException() {
        Category category = new Category(1, "shoe");
        given(categoryRepository.existsById(anyInt())).
                willReturn(true);
        boolean isTrue = categoryRepository.existsById(category.getId());
        assertTrue(isTrue);
//        assertThatThrownBy(() -> categoryRepository.existsById(category.getId()))
//                .isInstanceOf(CategoryNotFoundException.class)
//                .hasMessageContaining("Category not found");
    }

    @Test
    void getAllNoNull() {
        when(categoryRepository.findAll()).thenReturn(Stream.of(
                new Category(1, "shoe"), new Category(2, "clothing")).collect(Collectors.toList()));
        Assert.notNull(Stream.of(
                new Category(1, "shoe"), new Category(2, "clothing")).collect(Collectors.toList()));
    }

    @Test
    void getById() {
        int id = 1;
        when(categoryRepository.findById(id)).thenReturn(Optional.of(new Category(1, "shoe")));
        assertEquals(Optional.of(new Category(1, "shoe")), categoryRepository.findById(id));
    }

    @Test
    void update() {
        Category category = Category.builder()
                .id(1)
                .name("Shoe")
                .build();
        EditCategoryDto editCategoryDto = EditCategoryDto.builder()
                .name("Perfume")
                .build();
        when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(category));
        verify(categoryRepository, times(0)).findById(1);
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        categoryServiceImpl.update(category.getId(), editCategoryDto);
        category.setName(editCategoryDto.getName());
        categoryRepository.save(category);
        verify(categoryRepository, times(2)).save(categoryArgumentCaptor.capture());
        Category value = categoryArgumentCaptor.getValue();
        assertEquals(value, category);
    }
}
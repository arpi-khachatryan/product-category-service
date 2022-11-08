package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.dto.CategoryResponseDto;
import am.itspace.productcategoryservice.dto.CreateCategoryDto;
import am.itspace.productcategoryservice.dto.EditCategoryDto;
import am.itspace.productcategoryservice.exception.CategoryAlreadyExistsException;
import am.itspace.productcategoryservice.exception.CategoryNotFoundException;
import am.itspace.productcategoryservice.exception.Error;
import am.itspace.productcategoryservice.mapper.CategoryMapper;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.repository.CategoryRepository;
import am.itspace.productcategoryservice.security.CurrentUser;
import am.itspace.productcategoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto save(CreateCategoryDto createCategoryDto) {
        if (categoryRepository.existsByName(createCategoryDto.getName())) {
            log.info("Category with that name already exists {}", createCategoryDto.getName());
            throw new CategoryAlreadyExistsException(Error.CATEGORY_ALREADY_EXISTS);
        }
        log.info("The Category was successfully stored in the database {}", createCategoryDto.getName());
        return categoryMapper.mapToResponseDto(categoryRepository.save(categoryMapper.mapToEntity(createCategoryDto)));
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        log.info("Category successfully detected");
        return categoryMapper.mapToResponseDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto getById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(Error.CATEGORY_NOT_FOUND));
        log.info("Category successfully found {}", category.getName());
        return categoryMapper.mapToResponseDto(category);
    }

    @Override
    public CategoryResponseDto update(int id, EditCategoryDto editCategoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(Error.CATEGORY_NOT_FOUND));
        log.info("Category with that id not found");
        if (editCategoryDto.getName() != null) {
            category.setName(editCategoryDto.getName());
            categoryRepository.save(category);
        }
        log.info("The Category was successfully stored in the database {}", category.getName());
        return categoryMapper.mapToResponseDto(category);
    }

    @Override
    public void delete(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            log.info("The category has been successfully deleted");
        }
        log.info("Category not found");
        throw new CategoryNotFoundException(Error.CATEGORY_NOT_FOUND);
    }
}

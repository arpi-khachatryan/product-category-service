package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.dto.CategoryResponseDto;
import am.itspace.productcategoryservice.dto.CreateCategoryDto;
import am.itspace.productcategoryservice.dto.EditCategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAll();

    CategoryResponseDto getById(int id);

    void save(CreateCategoryDto createCategoryDto);

    CategoryResponseDto update(int id, EditCategoryDto editCategoryDto);

    void delete(int id);
}


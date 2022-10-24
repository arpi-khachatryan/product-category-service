package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.*;
import am.itspace.productcategoryservice.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapToEntity(CreateCategoryDto createCategoryDto);

    Category mapToEntity(EditCategoryDto editCategoryDto);

    CategoryResponseDto mapToResponseDto(Category category);

    List<CategoryResponseDto> mapToResponseDtoList(List<Category> category);
}

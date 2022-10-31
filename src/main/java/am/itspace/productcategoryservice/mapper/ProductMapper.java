package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.CreateProductDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapToEntity(CreateProductDto createProductDto);

    ProductResponseDto mapToResponseDto(Product product);

    List<ProductResponseDto> mapToResponseDtoList(List<Product> products);
}

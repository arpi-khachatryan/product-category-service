package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.dto.CreateProductDto;
import am.itspace.productcategoryservice.dto.EditProductDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;


import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAll();

    void save(CreateProductDto createProductDto);

    ProductResponseDto getById(int id);


    ProductResponseDto update(int id, EditProductDto editProductDto);

    void delete(int id);

    List<ProductResponseDto> getByCategory(int id);
}


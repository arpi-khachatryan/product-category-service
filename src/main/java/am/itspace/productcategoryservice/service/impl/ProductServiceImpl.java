package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.dto.CreateProductDto;
import am.itspace.productcategoryservice.dto.EditProductDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.exception.Error;
import am.itspace.productcategoryservice.exception.ProductAlreadyExistsException;
import am.itspace.productcategoryservice.exception.ProductNotFoundException;
import am.itspace.productcategoryservice.mapper.ProductMapper;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.repository.ProductRepository;
import am.itspace.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getAll() {
        log.info("Product successfully detected");
        return productMapper.mapToResponseDtoList(productRepository.findAll());
    }

    @Override
    public ProductResponseDto save(CreateProductDto createProductDto) {
        if (productRepository.existsByTitleAndPriceAndCategory(createProductDto.getTitle(), createProductDto.getPrice(), createProductDto.getCategory())) {
            log.info("Product with that title and price already exists");
            throw new ProductAlreadyExistsException(Error.PRODUCT_ALREADY_EXISTS);
        }
        log.info("The Product was successfully stored in the database {}", createProductDto.getTitle());
        return productMapper.mapToResponseDto(productRepository.save(productMapper.mapToEntity(createProductDto)));
    }

    @Override
    public ProductResponseDto getById(int id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(Error.PRODUCT_NOT_FOUND));
        log.info("Product successfully found {}", product.getTitle());
        return productMapper.mapToResponseDto(product);
    }

    @Override
    public ProductResponseDto update(int id, EditProductDto editProductDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(Error.PRODUCT_NOT_FOUND));
        if (editProductDto.getTitle() != null) {
            product.setTitle(editProductDto.getTitle());
        }
        if (editProductDto.getPrice() != null) {
            product.setPrice(editProductDto.getPrice());
        }
        if (editProductDto.getCategory() != null) {
            product.setCategory(editProductDto.getCategory());
        }
        log.info("The Product was successfully stored in the database {}", product.getTitle());
        return productMapper.mapToResponseDto(product);
    }

    @Override
    public void delete(int id) {
        if (!productRepository.existsById(id)) {
            log.info("Product not found");
            throw new ProductNotFoundException(Error.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
        log.info("The product has been successfully deleted");
    }

    @Override
    public List<ProductResponseDto> getByCategory(int id) {
        List<Product> products = productRepository.findAllByCategoryId(id);
        if (products == null || products.isEmpty()) {
            log.info("Product not found");
            throw new ProductNotFoundException(Error.PRODUCT_NOT_FOUND);
        }
        log.info("Product successfully detected");
        return productMapper.mapToResponseDtoList(products);
    }
}

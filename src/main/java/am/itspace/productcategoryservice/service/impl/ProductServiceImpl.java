package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.dto.CreateProductDto;
import am.itspace.productcategoryservice.dto.EditProductDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.exception.Error;
import am.itspace.productcategoryservice.exception.ObjectProcessingException;
import am.itspace.productcategoryservice.mapper.ProductMapper;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.repository.ProductRepository;
import am.itspace.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
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
    public void save(CreateProductDto createProductDto) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getTitle().equals(createProductDto.getTitle()) && product.getPrice() == createProductDto.getPrice()) {
                log.info("Product with that title and price already exists {}", product.getTitle());
                throw new ObjectProcessingException(Error.OBJECT_ALREADY_EXISTS);
            }
        }
        productRepository.save(productMapper.mapToEntity(createProductDto));
        log.info("The Product was successfully stored in the database {}", createProductDto.getTitle());
    }

    @Override
    public ProductResponseDto getById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.info("Product not found");
            throw new ObjectProcessingException(Error.OBJECT_NOT_FOUND);
        }
        log.info("Product successfully found {}", productOptional.get().getTitle());
        return productMapper.mapToResponseDto(productOptional.get());
    }

    @Override
    public ProductResponseDto update(int id, EditProductDto editProductDto) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.info("Product not found");
            throw new ObjectProcessingException(Error.OBJECT_NOT_FOUND);
        }
        Product product = productMapper.mapToEntity(editProductDto);
        product.setId(id);
        productRepository.save(product);
        log.info("The Product was successfully stored in the database {}", product.getTitle());
        return productMapper.mapToResponseDto(product);
    }

    @Override
    public void delete(int id) {
        if (!productRepository.existsById(id)) {
            log.info("Product not found");
            throw new ObjectProcessingException(Error.OBJECT_NOT_FOUND);
        }
        productRepository.deleteById(id);
        log.info("The product has been successfully deleted");
    }

    @Override
    public List<ProductResponseDto> getByCategory(int id) {
        List<Product> allCategories = productRepository.findAllByCategoryId(id);
        if (allCategories.isEmpty()) {
            log.info("Product not found");
            throw new ObjectProcessingException(Error.OBJECT_NOT_FOUND);
        }
        log.info("Product successfully detected");
        return productMapper.mapToResponseDtoList(allCategories);
    }
}

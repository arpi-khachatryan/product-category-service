package am.itspace.productcategoryservice.controller;

import am.itspace.productcategoryservice.dto.CategoryResponseDto;
import am.itspace.productcategoryservice.dto.CreateCategoryDto;
import am.itspace.productcategoryservice.dto.EditCategoryDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.service.CategoryService;
import am.itspace.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryEndpoint {

    private final CategoryService categoryService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CreateCategoryDto createCategoryDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(createCategoryDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") int id,
                                                      @RequestBody EditCategoryDto editCategoryDto) {
        return ResponseEntity.ok(categoryService.update(id, editCategoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.getByCategory(id));
    }
}

package am.itspace.productcategoryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

    CATEGORY_NOT_FOUND(4041, HttpStatus.NOT_FOUND, "Category not found"),

    CATEGORY_ALREADY_EXISTS(4001, HttpStatus.BAD_REQUEST, "Category already exists"),

    PRODUCT_NOT_FOUND(4041, HttpStatus.NOT_FOUND, "Product not found"),

    PRODUCT_ALREADY_EXISTS(4001, HttpStatus.BAD_REQUEST, "Product already exists");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}



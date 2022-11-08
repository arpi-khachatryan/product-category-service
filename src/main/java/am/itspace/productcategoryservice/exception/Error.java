package am.itspace.productcategoryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

    CATEGORY_NOT_FOUND(4041, HttpStatus.NOT_FOUND, "Category not found"),

    CATEGORY_ALREADY_EXISTS(4001, HttpStatus.BAD_REQUEST, "Category already exists"),

    PRODUCT_NOT_FOUND(4042, HttpStatus.NOT_FOUND, "Product not found"),

    PRODUCT_ALREADY_EXISTS(4002, HttpStatus.BAD_REQUEST, "Product already exists"),

    USER_REGISTRATION_FAILED(4091, HttpStatus.CONFLICT, "Email already exists"),

    USER_NOT_FOUND(4011, HttpStatus.UNAUTHORIZED, "User not found"),

    TO_DO_NOT_FOUND(4043, HttpStatus.NOT_FOUND, "ToDo not found"),

    TO_DO_IS_NUll(4044, HttpStatus.NOT_FOUND, "ToDo is null");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}



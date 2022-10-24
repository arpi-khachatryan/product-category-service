package am.itspace.productcategoryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

    OBJECT_NOT_FOUND(4041, HttpStatus.NOT_FOUND, "Object not found"),

    OBJECT_ALREADY_EXISTS(4042, HttpStatus.NOT_ACCEPTABLE, "Object already exists");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}



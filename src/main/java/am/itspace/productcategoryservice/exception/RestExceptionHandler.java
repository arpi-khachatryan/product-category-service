package am.itspace.productcategoryservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ApiError> handleIllegalArgument(BaseException ex) {
        Error error = ex.getError();
        ApiError apiError = new ApiError(error.getHttpStatus(), error.getCode(), LocalDateTime.now(), error.getMessage());

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}

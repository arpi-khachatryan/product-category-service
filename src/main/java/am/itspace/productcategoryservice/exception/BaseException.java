package am.itspace.productcategoryservice.exception;

import lombok.Setter;
import lombok.Getter;

/**
 * @author Arpi Khachatryan on 24.10.2022
 */

@Setter
@Getter
public class BaseException extends RuntimeException {

    protected final Error error;

    public BaseException(Error error) {
        this.error = error;
    }
}

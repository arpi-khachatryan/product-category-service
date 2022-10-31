package am.itspace.productcategoryservice.exception;

public class AuthenticationException extends BaseException {

    public AuthenticationException(Error error) {
        super(error);
    }
}

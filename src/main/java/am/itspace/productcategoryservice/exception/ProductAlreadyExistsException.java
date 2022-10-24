package am.itspace.productcategoryservice.exception;

public class ProductAlreadyExistsException extends BaseException {

    public ProductAlreadyExistsException(Error error) {
        super(error);
    }
}

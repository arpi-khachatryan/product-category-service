package am.itspace.productcategoryservice.exception;

public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException(Error error) {
        super(error);
    }
}

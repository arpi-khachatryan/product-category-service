package am.itspace.productcategoryservice.exception;

public class CategoryAlreadyExistsException extends BaseException {

    public CategoryAlreadyExistsException(Error error) {
        super(error);
    }
}

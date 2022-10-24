package am.itspace.productcategoryservice.exception;

public class CategoryNotFoundException extends BaseException {

    public CategoryNotFoundException(Error error) {
        super(error);
    }
}

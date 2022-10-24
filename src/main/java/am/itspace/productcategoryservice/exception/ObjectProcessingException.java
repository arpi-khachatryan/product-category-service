package am.itspace.productcategoryservice.exception;

public class ObjectProcessingException extends BaseException {

    public ObjectProcessingException(Error error) {
        super(error);
    }
}

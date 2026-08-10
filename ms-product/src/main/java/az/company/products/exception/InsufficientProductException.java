package az.company.products.exception;

public class InsufficientProductException extends RuntimeException {
    public InsufficientProductException(String message) {
        super(message);
    }
}

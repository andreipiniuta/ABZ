package sample;

public class CanNotCreateWindowException extends RuntimeException {
    public CanNotCreateWindowException(WindowStorage windowStorage, Throwable cause) {
        super("Can not create Window: " + windowStorage, cause);
    }
}

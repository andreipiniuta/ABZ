package sample;

public class UnknownWindowException extends RuntimeException {
    public UnknownWindowException(WindowStorage windowStorage) {
        super("Can not find window with name - " + windowStorage);
    }

}

package nl.harvest.insurance.error;

public final class UnknownException extends RuntimeException {

    public UnknownException() {
        super();
    }

    public UnknownException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnknownException(final String message) {
        super(message);
    }

    public UnknownException(final Throwable cause) {
        super(cause);
    }

}

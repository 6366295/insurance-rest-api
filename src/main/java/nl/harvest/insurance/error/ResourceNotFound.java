package nl.harvest.insurance.error;

public final class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
        super();
    }

    public ResourceNotFound(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFound(final String message) {
        super(message);
    }

    public ResourceNotFound(final Throwable cause) {
        super(cause);
    }

}

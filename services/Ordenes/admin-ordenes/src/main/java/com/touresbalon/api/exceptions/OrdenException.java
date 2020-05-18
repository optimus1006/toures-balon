package com.touresbalon.api.exceptions;

public class OrdenException extends RuntimeException {

	public OrdenException() {
        super();
    }

	public OrdenException(String message) {
        super(message);
    }

	public OrdenException(String message, Throwable cause) {
        super(message, cause);
    }

	public OrdenException(Throwable cause) {
        super(cause);
    }

	protected OrdenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

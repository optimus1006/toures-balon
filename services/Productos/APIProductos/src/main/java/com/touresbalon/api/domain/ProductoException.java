package com.touresbalon.api.domain;

public class ProductoException extends RuntimeException {
	
	public ProductoException() {
        super();
    }

	public ProductoException(String message) {
        super(message);
    }

	public ProductoException(String message, Throwable cause) {
        super(message, cause);
    }

	public ProductoException(Throwable cause) {
        super(cause);
    }

	protected ProductoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

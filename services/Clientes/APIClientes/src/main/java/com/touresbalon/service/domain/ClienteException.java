package com.touresbalon.service.domain;

public class ClienteException extends RuntimeException {
	
	public ClienteException() {
        super();
    }

	public ClienteException(String message) {
        super(message);
    }

	public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }

	public ClienteException(Throwable cause) {
        super(cause);
    }

	protected ClienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

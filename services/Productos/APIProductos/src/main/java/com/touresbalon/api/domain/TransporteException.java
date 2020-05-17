package com.touresbalon.api.domain;

public class TransporteException extends RuntimeException {
	
	public TransporteException() {
        super();
    }

	public TransporteException(String message) {
        super(message);
    }

	public TransporteException(String message, Throwable cause) {
        super(message, cause);
    }

	public TransporteException(Throwable cause) {
        super(cause);
    }

	protected TransporteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.touresbalon.api.domain;

public class HospedajeException extends RuntimeException {
	
	public HospedajeException() {
        super();
    }

	public HospedajeException(String message) {
        super(message);
    }

	public HospedajeException(String message, Throwable cause) {
        super(message, cause);
    }

	public HospedajeException(Throwable cause) {
        super(cause);
    }

	protected HospedajeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

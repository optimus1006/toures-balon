package com.touresbalon.api.domain;

public class EventoException extends RuntimeException {
	
	public EventoException() {
        super();
    }

	public EventoException(String message) {
        super(message);
    }

	public EventoException(String message, Throwable cause) {
        super(message, cause);
    }

	public EventoException(Throwable cause) {
        super(cause);
    }

	protected EventoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

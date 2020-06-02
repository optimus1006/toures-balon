package com.touresbalon.api.exceptions;

public class KafkaException extends RuntimeException {

	public KafkaException() {
        super();
    }

	public KafkaException(String message) {
        super(message);
    }

	public KafkaException(String message, Throwable cause) {
        super(message, cause);
    }

	public KafkaException(Throwable cause) {
        super(cause);
    }

	protected KafkaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

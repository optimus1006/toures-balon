package com.touresbalon.convenios.exceptions;

public class ConvenioNotFoundException extends RuntimeException {

	public ConvenioNotFoundException() {
        super();
    }

    public ConvenioNotFoundException(String message) {
        super(message);
    }

}

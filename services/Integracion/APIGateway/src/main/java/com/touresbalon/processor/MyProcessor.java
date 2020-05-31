package com.touresbalon.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.touresbalon.model.PrcViajesElement;

public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		PrcViajesElement prcViajesElement = exchange.getIn().getBody(PrcViajesElement.class);
		exchange.getIn().setBody(prcViajesElement);
	}

}
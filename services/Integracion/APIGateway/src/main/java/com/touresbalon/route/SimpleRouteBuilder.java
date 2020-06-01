package com.touresbalon.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.touresbalon.ParamDefinitions;
import com.touresbalon.model.Viajes;
import com.touresbalon.processor.BolivarianoProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// XML Data Format
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Viajes.class);
		xmlDataFormat.setContext(con);

		// JSON Data Format
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Viajes.class);

		from(ParamDefinitions._CONST_FILE_ENDPOINT_CSV).doTry().process(new BolivarianoProcessor()).marshal(jsonDataFormat)
				.to("jms:queue:busesBolivariano").doCatch(Exception.class).process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
						System.out.println(cause);
					}
				});
	}
}
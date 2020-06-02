package com.touresbalon.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.touresbalon.model.Viajes;

public class BolivarianoProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		String myString = exchange.getIn().getBody(String.class);
		String[] lineSeparator = myString.split(System.getProperty("line.separator"));
		StringBuffer sb = new StringBuffer();
		sb.append("<viajes>");
		for (String lineData : lineSeparator) {
			String[] commaSeparator = lineData.split(",");
			sb.append("<sillas>");
			sb.append("<numViaje>" + commaSeparator[0].toString() + "</numViaje>");
			sb.append("<ciudadOrigen>" + commaSeparator[1].toString() + "</ciudadOrigen>");
			sb.append("<ciudadDestino>" + commaSeparator[2].toString() + "</ciudadDestino>");
			sb.append("<numSilla>" + commaSeparator[3].toString() + "</numSilla>");
			sb.append("<horaSalida>" + commaSeparator[4].toString() + "</horaSalida>");
			sb.append("</sillas>");
		}
		sb.append("</viajes>");
		System.out.println("BolivarianoProcessor complete");
		exchange.getIn().setBody(sb.toString());

		Viajes viajes = exchange.getIn().getBody(Viajes.class);
		exchange.getIn().setBody(viajes);
	}

}
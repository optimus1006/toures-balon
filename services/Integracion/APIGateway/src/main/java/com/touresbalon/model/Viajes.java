package com.touresbalon.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "viajes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Viajes {

	private Sillas[] sillas;

	public Sillas[] getSillas() {
		return sillas;
	}

	public void setSillas(Sillas[] sillas) {
		this.sillas = sillas;
	}

}

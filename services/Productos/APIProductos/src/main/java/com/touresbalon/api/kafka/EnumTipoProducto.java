package com.touresbalon.api.kafka;

public enum EnumTipoProducto {
	
	TRANSPORTE("0"), HOSPEDAJE("1"), EVENTO("2");

	public final String valor;

	private EnumTipoProducto(String element) {
		this.valor = element;
	}

}

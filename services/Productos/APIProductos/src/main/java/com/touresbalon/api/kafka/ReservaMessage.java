package com.touresbalon.api.kafka;

import com.touresbalon.api.domain.Cliente;

public class ReservaMessage {
	
	private String idConvenio;
	
	private String codigoExterno;
	
	private String codigoExternoDetalle;
	
	private String tipoProducto;
	
	private Long idOrden;
	
	private int cantidadProductosReserva;
	
	private Cliente cliente;
	
	private Long idProducto;
	
	private Long idProductoDetalle;

	public String getCodigoExternoDetalle() {
		return codigoExternoDetalle;
	}

	public void setCodigoExternoDetalle(String codigoExternoDetalle) {
		this.codigoExternoDetalle = codigoExternoDetalle;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdProductoDetalle() {
		return idProductoDetalle;
	}

	public void setIdProductoDetalle(Long idProductoDetalle) {
		this.idProductoDetalle = idProductoDetalle;
	}

	public String getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Long getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	public int getCantidadProductosReserva() {
		return cantidadProductosReserva;
	}

	public void setCantidadProductosReserva(int cantidadProductosReserva) {
		this.cantidadProductosReserva = cantidadProductosReserva;
	}
	
}

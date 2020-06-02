package com.touresbalon.ordenes.kafka;

import com.touresbalon.ordenes.util.EnumTipoProducto;
import com.touresbalon.ordenes.api.model.Cliente;

public class ReservaMessage {
	
	private Long idConvenio;
	
	private String codigoExterno;
	
	private String codigoExternoDetalle;
	
	private EnumTipoProducto tipoProducto;
	
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

	public Long getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public EnumTipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(EnumTipoProducto tipoProducto) {
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

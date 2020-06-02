package com.touresbalon.kafka;

import com.touresbalon.api.domain.Cliente;
import com.touresbalon.api.domain.EnumTipoProducto;

import java.util.Objects;

public class ReservaMessage {

    private String idConvenio;

    private Long codigoExterno;

    private String codigoExternoDetalle;

    private EnumTipoProducto tipoProducto;

    private Long idOrden;

    private int cantidadProductosReserva;

    private Long idProducto;

    private Long idProductoDetalle;

    private Cliente cliente;

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

    public Long getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(Long codigoExterno) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReservaMessage {\n");
        sb.append("    CodigoExterno: ").append(toIndentedString(getCodigoExterno())).append("\n");
        sb.append("    IdConvenio: ").append(toIndentedString(getIdConvenio())).append("\n");
        sb.append("    IdProducto: ").append(toIndentedString(getIdProducto())).append("\n");
        sb.append("    TipoProducto: ").append(toIndentedString(getTipoProducto())).append("\n");
        sb.append("    CantidadProductosReserva: ").append(toIndentedString(getCantidadProductosReserva())).append("\n");
        sb.append("    Cliente: ").append(toIndentedString(getCliente())).append("\n");
        sb.append("    CodigoExternoDetalle: ").append(toIndentedString(getCodigoExternoDetalle())).append("\n");
        sb.append("    IdOrden: ").append(toIndentedString(getIdOrden())).append("\n");
        sb.append("    IdProductoDetalle: ").append(toIndentedString(getIdProductoDetalle())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservaMessage reservaMessage = (ReservaMessage) o;
        return Objects.equals(this.cantidadProductosReserva, reservaMessage.cantidadProductosReserva) &&
                Objects.equals(this.cliente, reservaMessage.cliente) &&
                Objects.equals(this.codigoExterno, reservaMessage.codigoExterno) &&
                Objects.equals(this.codigoExternoDetalle, reservaMessage.codigoExternoDetalle) &&
                Objects.equals(this.idConvenio, reservaMessage.idConvenio) &&
                Objects.equals(this.idOrden, reservaMessage.idOrden) &&
                Objects.equals(this.idProducto, reservaMessage.idProducto) &&
                Objects.equals(this.idProductoDetalle, reservaMessage.idProductoDetalle) &&
                Objects.equals(this.cantidadProductosReserva, reservaMessage.cantidadProductosReserva) &&
                Objects.equals(this.tipoProducto, reservaMessage.tipoProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidadProductosReserva, cliente, codigoExterno, codigoExternoDetalle, idConvenio, idOrden, idProducto, idProductoDetalle, cantidadProductosReserva, tipoProducto);
    }
}

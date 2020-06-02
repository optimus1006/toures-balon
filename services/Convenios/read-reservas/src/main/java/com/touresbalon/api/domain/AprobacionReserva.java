package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AprobacionReserva {

    @JsonProperty("codigoExterno")
    private String codigoExterno;
    @JsonProperty("codigoExternoDetalle")
    private String codigoExternoDetalle;
    @JsonProperty("tipoProducto")
    private int tipoProducto;
    @JsonProperty("idOrden")
    private long idOrden;
    @JsonProperty("detalle")
    private String detalle;
    @JsonProperty("idProducto")
    private long idProducto;
    @JsonProperty("idProductoDetalle")
    private long idProductoDetalle;
    @JsonProperty("codigoAprobacion")
    private String codigoAprobacion;

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

    public String getCodigoExternoDetalle() {
        return codigoExternoDetalle;
    }

    public void setCodigoExternoDetalle(String codigoExternoDetalle) {
        this.codigoExternoDetalle = codigoExternoDetalle;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIdProductoDetalle() {
        return idProductoDetalle;
    }

    public void setIdProductoDetalle(long idProductoDetalle) {
        this.idProductoDetalle = idProductoDetalle;
    }

    public String getCodigoAprobacion() {
        return codigoAprobacion;
    }

    public void setCodigoAprobacion(String codigoAprobacion) {
        this.codigoAprobacion = codigoAprobacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AprobacionReserva aprobacionReserva = (AprobacionReserva) o;
        return Objects.equals(this.codigoExterno, aprobacionReserva.codigoExterno) &&
                Objects.equals(this.codigoExternoDetalle, aprobacionReserva.codigoExternoDetalle) &&
                Objects.equals(this.tipoProducto, aprobacionReserva.tipoProducto) &&
                Objects.equals(this.idOrden, aprobacionReserva.idOrden) &&
                Objects.equals(this.detalle, aprobacionReserva.detalle) &&
                Objects.equals(this.idProducto, aprobacionReserva.idProducto) &&
                Objects.equals(this.idProductoDetalle, aprobacionReserva.idProductoDetalle) &&
                Objects.equals(this.codigoAprobacion, aprobacionReserva.codigoAprobacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoExterno, codigoExternoDetalle, tipoProducto, idOrden, detalle, idProducto, idProductoDetalle, codigoAprobacion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Factura {\n");

        sb.append("    codigoExterno: ").append(toIndentedString(codigoExterno)).append("\n");
        sb.append("    codigoExternoDetalle: ").append(toIndentedString(codigoExternoDetalle)).append("\n");
        sb.append("    tipoProducto: ").append(toIndentedString(tipoProducto)).append("\n");
        sb.append("    idOrden: ").append(toIndentedString(idOrden)).append("\n");
        sb.append("    detalle: ").append(toIndentedString(detalle)).append("\n");
        sb.append("    idProducto: ").append(toIndentedString(idProducto)).append("\n");
        sb.append("    idProductoDetalle: ").append(toIndentedString(idProductoDetalle)).append("\n");
        sb.append("    codigoAprobacion: ").append(toIndentedString(codigoAprobacion)).append("\n");
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
}

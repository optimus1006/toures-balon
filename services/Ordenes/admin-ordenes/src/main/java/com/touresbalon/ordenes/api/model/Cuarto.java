package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Espacio, habitacion o lugar que pertenece a un hospedaje
 */
@ApiModel(description = "Espacio, habitacion o lugar que pertenece a un hospedaje")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-13T20:28:44.608-05:00[America/Bogota]")

public class Cuarto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("numeroCuarto")
  private String numeroCuarto;

  @JsonProperty("idCliente")
  private com.touresbalon.ordenes.api.model.Cliente idCliente;

  @JsonProperty("fechaReservaInicio")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaReservaInicio;

  @JsonProperty("fechaReservaFin")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaReservaFin;

  @JsonProperty("fechaReserva")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaReserva;

  public com.touresbalon.ordenes.api.model.Cuarto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * identificador unico del cuarto
   * @return id
  */
  @ApiModelProperty(value = "identificador unico del cuarto")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public com.touresbalon.ordenes.api.model.Cuarto numeroCuarto(String numeroCuarto) {
    this.numeroCuarto = numeroCuarto;
    return this;
  }

  /**
   * identificador unico del cuarto en el hospedaje
   * @return numeroCuarto
  */
  @ApiModelProperty(value = "identificador unico del cuarto en el hospedaje")


  public String getNumeroCuarto() {
    return numeroCuarto;
  }

  public void setNumeroCuarto(String numeroCuarto) {
    this.numeroCuarto = numeroCuarto;
  }

  public com.touresbalon.ordenes.api.model.Cuarto idCliente(com.touresbalon.ordenes.api.model.Cliente idCliente) {
    this.idCliente = idCliente;
    return this;
  }

  /**
   * Get idCliente
   * @return idCliente
  */
  @ApiModelProperty(value = "")

  @Valid

  public com.touresbalon.ordenes.api.model.Cliente getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(com.touresbalon.ordenes.api.model.Cliente idCliente) {
    this.idCliente = idCliente;
  }

  public com.touresbalon.ordenes.api.model.Cuarto fechaReservaInicio(LocalDateTime fechaReservaInicio) {
    this.fechaReservaInicio = fechaReservaInicio;
    return this;
  }

  /**
   * fecha de inicio de la reserva del cuarto
   * @return fechaReservaInicio
  */
  @ApiModelProperty(value = "fecha de inicio de la reserva del cuarto")

  @Valid

  public LocalDateTime getFechaReservaInicio() {
    return fechaReservaInicio;
  }

  public void setFechaReservaInicio(LocalDateTime fechaReservaInicio) {
    this.fechaReservaInicio = fechaReservaInicio;
  }

  public com.touresbalon.ordenes.api.model.Cuarto fechaReservaFin(LocalDateTime fechaReservaFin) {
    this.fechaReservaFin = fechaReservaFin;
    return this;
  }

  /**
   * fecha de fin de la reserva del cuarto
   * @return fechaReservaFin
  */
  @ApiModelProperty(value = "fecha de fin de la reserva del cuarto")

  @Valid

  public LocalDateTime getFechaReservaFin() {
    return fechaReservaFin;
  }

  public void setFechaReservaFin(LocalDateTime fechaReservaFin) {
    this.fechaReservaFin = fechaReservaFin;
  }

  public com.touresbalon.ordenes.api.model.Cuarto fechaReserva(LocalDateTime fechaReserva) {
    this.fechaReserva = fechaReserva;
    return this;
  }

  /**
   * fecha en que fue realizada la reserva
   * @return fechaReserva
  */
  @ApiModelProperty(value = "fecha en que fue realizada la reserva")

  @Valid

  public LocalDateTime getFechaReserva() {
    return fechaReserva;
  }

  public void setFechaReserva(LocalDateTime fechaReserva) {
    this.fechaReserva = fechaReserva;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    com.touresbalon.ordenes.api.model.Cuarto cuarto = (com.touresbalon.ordenes.api.model.Cuarto) o;
    return Objects.equals(this.id, cuarto.id) &&
        Objects.equals(this.numeroCuarto, cuarto.numeroCuarto) &&
        Objects.equals(this.idCliente, cuarto.idCliente) &&
        Objects.equals(this.fechaReservaInicio, cuarto.fechaReservaInicio) &&
        Objects.equals(this.fechaReservaFin, cuarto.fechaReservaFin) &&
        Objects.equals(this.fechaReserva, cuarto.fechaReserva);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numeroCuarto, idCliente, fechaReservaInicio, fechaReservaFin, fechaReserva);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cuarto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    numeroCuarto: ").append(toIndentedString(numeroCuarto)).append("\n");
    sb.append("    idCliente: ").append(toIndentedString(idCliente)).append("\n");
    sb.append("    fechaReservaInicio: ").append(toIndentedString(fechaReservaInicio)).append("\n");
    sb.append("    fechaReservaFin: ").append(toIndentedString(fechaReservaFin)).append("\n");
    sb.append("    fechaReserva: ").append(toIndentedString(fechaReserva)).append("\n");
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


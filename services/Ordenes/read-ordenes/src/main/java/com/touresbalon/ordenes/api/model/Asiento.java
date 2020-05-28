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
 * Puestos, cillas asociadas a un medio de transporte
 */
@ApiModel(description = "Puestos, cillas asociadas a un medio de transporte")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T13:11:16.793-05:00[America/Bogota]")

public class Asiento   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("valor")
  private String valor;

  @JsonProperty("idCliente")
  private Cliente idCliente;

  @JsonProperty("fechaReserva")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaReserva;

  public Asiento id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Asiento valor(String valor) {
    this.valor = valor;
    return this;
  }

  /**
   * valor de asignacion del asiento
   * @return valor
  */
  @ApiModelProperty(value = "valor de asignacion del asiento")


  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public Asiento idCliente(Cliente idCliente) {
    this.idCliente = idCliente;
    return this;
  }

  /**
   * Get idCliente
   * @return idCliente
  */
  @ApiModelProperty(value = "")

  @Valid

  public Cliente getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Cliente idCliente) {
    this.idCliente = idCliente;
  }

  public Asiento fechaReserva(LocalDateTime fechaReserva) {
    this.fechaReserva = fechaReserva;
    return this;
  }

  /**
   * Get fechaReserva
   * @return fechaReserva
  */
  @ApiModelProperty(value = "")

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
    Asiento asiento = (Asiento) o;
    return Objects.equals(this.id, asiento.id) &&
        Objects.equals(this.valor, asiento.valor) &&
        Objects.equals(this.idCliente, asiento.idCliente) &&
        Objects.equals(this.fechaReserva, asiento.fechaReserva);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, valor, idCliente, fechaReserva);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Asiento {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    valor: ").append(toIndentedString(valor)).append("\n");
    sb.append("    idCliente: ").append(toIndentedString(idCliente)).append("\n");
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


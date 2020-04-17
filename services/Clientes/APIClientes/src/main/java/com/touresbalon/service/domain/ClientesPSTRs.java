package com.touresbalon.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.service.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta a la creacion de un cliente
 */
@ApiModel(description = "Respuesta a la creacion de un cliente")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-04-09T13:17:10.375-05:00[America/Bogota]")

public class ClientesPSTRs   {
  @JsonProperty("cliente")
  private Cliente cliente = null;

  public ClientesPSTRs cliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  /**
   * Get cliente
   * @return cliente
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientesPSTRs clientesPSTRs = (ClientesPSTRs) o;
    return Objects.equals(this.cliente, clientesPSTRs.cliente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cliente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientesPSTRs {\n");
    
    sb.append("    cliente: ").append(toIndentedString(cliente)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


package com.touresbalon.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.service.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta a consulta de todos los clientes
 */
@ApiModel(description = "Respuesta a consulta de todos los clientes")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-04-09T13:17:10.375-05:00[America/Bogota]")

public class ClientesGETAllRS   {
  @JsonProperty("clientes")
  @Valid
  private List<Cliente> clientes = new ArrayList<>();

  public ClientesGETAllRS clientes(List<Cliente> clientes) {
    this.clientes = clientes;
    return this;
  }

  public ClientesGETAllRS addClientesItem(Cliente clientesItem) {
    this.clientes.add(clientesItem);
    return this;
  }

  /**
   * Get clientes
   * @return clientes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(List<Cliente> clientes) {
    this.clientes = clientes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientesGETAllRS clientesGETAllRS = (ClientesGETAllRS) o;
    return Objects.equals(this.clientes, clientesGETAllRS.clientes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    
    sb.append("    clientes: ").append(toIndentedString(clientes)).append("\n");
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


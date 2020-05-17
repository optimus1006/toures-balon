package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Evento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta a consulta de todos los eventos
 */
@ApiModel(description = "Respuesta a consulta de todos los eventos")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class EventosGETAllRS   {
  @JsonProperty("eventos")
  @Valid
  private List<Evento> eventos = new ArrayList<>();

  public EventosGETAllRS eventos(List<Evento> eventos) {
    this.eventos = eventos;
    return this;
  }

  public EventosGETAllRS addEventosItem(Evento eventosItem) {
    this.eventos.add(eventosItem);
    return this;
  }

  /**
   * Get eventos
   * @return eventos
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Evento> getEventos() {
    return eventos;
  }

  public void setEventos(List<Evento> eventos) {
    this.eventos = eventos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventosGETAllRS eventosGETAllRS = (EventosGETAllRS) o;
    return Objects.equals(this.eventos, eventosGETAllRS.eventos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventosGETAllRS {\n");
    
    sb.append("    eventos: ").append(toIndentedString(eventos)).append("\n");
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


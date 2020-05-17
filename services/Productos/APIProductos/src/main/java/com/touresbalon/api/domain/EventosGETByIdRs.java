package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Evento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta a la consulta de un evento por Id
 */
@ApiModel(description = "Respuesta a la consulta de un evento por Id")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class EventosGETByIdRs   {
  @JsonProperty("evento")
  private Evento evento;

  public EventosGETByIdRs evento(Evento evento) {
    this.evento = evento;
    return this;
  }

  /**
   * Get evento
   * @return evento
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Evento getEvento() {
    return evento;
  }

  public void setEvento(Evento evento) {
    this.evento = evento;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventosGETByIdRs eventosGETByIdRs = (EventosGETByIdRs) o;
    return Objects.equals(this.evento, eventosGETByIdRs.evento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventosGETByIdRs {\n");
    
    sb.append("    evento: ").append(toIndentedString(evento)).append("\n");
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


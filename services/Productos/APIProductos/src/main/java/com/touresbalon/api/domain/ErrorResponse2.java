package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Error2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de la operación de un servicio.
 */
@ApiModel(description = "Respuesta de la operación de un servicio.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class ErrorResponse2   {
  @JsonProperty("message")
  private String message;

  @JsonProperty("error")
  @Valid
  private List<Error2> error = null;

  public ErrorResponse2 message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Descripción del error general.
   * @return message
  */
  @ApiModelProperty(value = "Descripción del error general.")

@Size(min=1,max=500) 
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResponse2 error(List<Error2> error) {
    this.error = error;
    return this;
  }

  public ErrorResponse2 addErrorItem(Error2 errorItem) {
    if (this.error == null) {
      this.error = new ArrayList<>();
    }
    this.error.add(errorItem);
    return this;
  }

  /**
   * Lista de los errores específicos.
   * @return error
  */
  @ApiModelProperty(value = "Lista de los errores específicos.")

  @Valid

  public List<Error2> getError() {
    return error;
  }

  public void setError(List<Error2> error) {
    this.error = error;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse2 errorResponse2 = (ErrorResponse2) o;
    return Objects.equals(this.message, errorResponse2.message) &&
        Objects.equals(this.error, errorResponse2.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, error);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse2 {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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


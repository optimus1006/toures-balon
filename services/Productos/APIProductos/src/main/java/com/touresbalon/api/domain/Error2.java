package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Error específico que describe un problema presentado al ejecutar una operación.
 */
@ApiModel(description = "Error específico que describe un problema presentado al ejecutar una operación.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Error2   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("id")
  private String id;

  @JsonProperty("message")
  private String message;

  @JsonProperty("path")
  private String path;

  @JsonProperty("url")
  private String url;

  public Error2 code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Código específico del error.
   * @return code
  */
  @ApiModelProperty(value = "Código específico del error.")

@Size(min=1,max=128) 
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Error2 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Id de referencia única para propósitos de auditoría.
   * @return id
  */
  @ApiModelProperty(value = "Id de referencia única para propósitos de auditoría.")

@Size(min=1,max=40) 
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Error2 message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Descripción del error específico.
   * @return message
  */
  @ApiModelProperty(value = "Descripción del error específico.")

@Size(min=1,max=500) 
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error2 path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Al referirse de un error debido a un campo específico, se describe la ruta del campo.
   * @return path
  */
  @ApiModelProperty(value = "Al referirse de un error debido a un campo específico, se describe la ruta del campo.")

@Size(min=1,max=500) 
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Error2 url(String url) {
    this.url = url;
    return this;
  }

  /**
   * URL de la documentación en donde se encuentra mayor información sobre el error.
   * @return url
  */
  @ApiModelProperty(value = "URL de la documentación en donde se encuentra mayor información sobre el error.")

@Size(min=1,max=500) 
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error2 error2 = (Error2) o;
    return Objects.equals(this.code, error2.code) &&
        Objects.equals(this.id, error2.id) &&
        Objects.equals(this.message, error2.message) &&
        Objects.equals(this.path, error2.path) &&
        Objects.equals(this.url, error2.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, id, message, path, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error2 {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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


package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ConveniosGETByIdRs
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Convenio {
  @JsonProperty("nombreProveedor")
  private String nombreProveedor;

  @JsonProperty("identificacion")
  private String identificacion;

  /**
   * Tipo de convenio.
   */
  public enum TipoConvenioEnum {
    TRANSPORTE("0"),
    
    HOSPEDAJE("1"),
    
    EVENTO("2");

    private String value;

    TipoConvenioEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TipoConvenioEnum fromValue(String value) {
      for (TipoConvenioEnum b : TipoConvenioEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  /**
   * Estado de convenio.
   */
  public enum EstadoConvenioEnum {
    TRANSPORTE("0"),

    HOSPEDAJE("1");

    private String value;

    EstadoConvenioEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EstadoConvenioEnum fromValue(String value) {
      for (EstadoConvenioEnum b : EstadoConvenioEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("tipoConvenio")
  private TipoConvenioEnum tipoConvenio;

  @JsonProperty("estadoConvenio")
  private EstadoConvenioEnum estadoConvenio;

  @JsonProperty("fechaVigencia")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "YYYY-MM-DDThh:mm:ss.SSS Z")
  private LocalDateTime fechaVigencia;

  @JsonProperty("correo")
  private String correo;

  @JsonProperty("pais")
  @JsonIgnore
  private Pais pais;

  @JsonProperty("ciudad")
  @JsonIgnore
  private Ciudad ciudad;

  @JsonProperty("paisCodigo")
  @JsonIgnore
  private int paisCodigo;

  @JsonProperty("ciudadCodigo")
  @JsonIgnore
  private int ciudadCodigo;

  @JsonProperty("endpoint")
  private String endpoint;

  @JsonProperty("templateEntrada")
  private String templateEntrada;

  @JsonProperty("templateSalida")
  private String templateSalida;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint){
    this.endpoint = endpoint;
  }

  public String getTemplateEntrada() {
    return templateEntrada;
  }

  public void setTemplateEntrada(String templateEntrada){
    this.templateEntrada = templateEntrada;
  }

  public String getTemplateSalida() {
    return templateSalida;
  }

  public void setTemplateSalida(String templateSalida){
    this.templateSalida = templateSalida;
  }

  public Convenio nombreProveedor(String nombreProveedor) {
    this.nombreProveedor = nombreProveedor;
    return this;
  }

  /**
   * Nombre del proveedor de servicios.
   * @return nombreProveedor
  */
  @ApiModelProperty(value = "Nombre del proveedor de servicios.")

@Size(min=1,max=254) 
  public String getNombreProveedor() {
    return nombreProveedor;
  }

  public void setNombreProveedor(String nombreProveedor) {
    this.nombreProveedor = nombreProveedor;
  }

  public Convenio identificacion(String identificacion) {
    this.identificacion = identificacion;
    return this;
  }

  /**
   * Identificación del proveedor de servicios.
   * @return identificacion
  */
  @ApiModelProperty(value = "Identificación del proveedor de servicios.")

@Size(min=1,max=40) 
  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public Convenio tipoConvenio(TipoConvenioEnum tipoConvenio) {
    this.tipoConvenio = tipoConvenio;
    return this;
  }

  /**
   * Tipo de convenio.
   * @return tipoConvenio
  */
  @ApiModelProperty(value = "Tipo de convenio.")


  public TipoConvenioEnum getTipoConvenio() {
    return tipoConvenio;
  }

  public void setTipoConvenio(TipoConvenioEnum tipoConvenio) {
    this.tipoConvenio = tipoConvenio;
  }

  public Convenio fechaVigencia(LocalDateTime fechaVigencia) {
    this.fechaVigencia = fechaVigencia;
    return this;
  }

  /**
   * Vigencia del convenio.
   * @return fechaVigencia
  */
  @ApiModelProperty(value = "Vigencia del convenio.")

  @Valid

  public LocalDateTime getFechaVigencia() {
    return fechaVigencia;
  }

  public void setFechaVigencia(LocalDateTime fechaVigencia) {
    this.fechaVigencia = fechaVigencia;
  }

  public Convenio correo(String correo) {
    this.correo = correo;
    return this;
  }

  /**
   * Correo de contacto del convenio.
   * @return correo
  */
  @ApiModelProperty(value = "Correo de contacto del convenio.")

@Size(min=1,max=90) 
  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public Convenio pais(Pais pais) {
    this.pais = pais;
    return this;
  }

  /**
   * Get pais
   * @return pais
  */
  @ApiModelProperty(value = "")

  @Valid

  public Pais getPais() {
    return pais;
  }

  public void setPais(Pais pais) {
    this.pais = pais;
  }

  public Convenio ciudad(Ciudad ciudad) {
    this.ciudad = ciudad;
    return this;
  }

  /**
   * Get ciudad
   * @return ciudad
  */
  @ApiModelProperty(value = "")

  @Valid

  public Ciudad getCiudad() {
    return ciudad;
  }

  public void setCiudad(Ciudad ciudad) {
    this.ciudad = ciudad;
  }

  public EstadoConvenioEnum getEstadoConvenio() {
    return estadoConvenio;
  }

  public void setEstadoConvenio(EstadoConvenioEnum estadoConvenio) {
    this.estadoConvenio = estadoConvenio;
  }

  public int getPaisCodigo() {
    return paisCodigo;
  }

  public void setPaisCodigo(int paisCodigo) {
    this.paisCodigo = paisCodigo;
  }

  public int getCiudadCodigo() {
    return ciudadCodigo;
  }

  public void setCiudadCodigo(int ciudadCodigo) {
    this.ciudadCodigo = ciudadCodigo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Convenio convenio = (Convenio) o;
    return Objects.equals(this.nombreProveedor, convenio.nombreProveedor) &&
        Objects.equals(this.identificacion, convenio.identificacion) &&
        Objects.equals(this.tipoConvenio, convenio.tipoConvenio) &&
        Objects.equals(this.fechaVigencia, convenio.fechaVigencia) &&
        Objects.equals(this.correo, convenio.correo) &&
        Objects.equals(this.pais, convenio.pais) &&
        Objects.equals(this.ciudad, convenio.ciudad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombreProveedor, identificacion, tipoConvenio, fechaVigencia, correo, pais, ciudad);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConveniosGETByIdRs {\n");
    
    sb.append("    nombreProveedor: ").append(toIndentedString(nombreProveedor)).append("\n");
    sb.append("    identificacion: ").append(toIndentedString(identificacion)).append("\n");
    sb.append("    tipoConvenio: ").append(toIndentedString(tipoConvenio)).append("\n");
    sb.append("    fechaVigencia: ").append(toIndentedString(fechaVigencia)).append("\n");
    sb.append("    correo: ").append(toIndentedString(correo)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
    sb.append("    ciudad: ").append(toIndentedString(ciudad)).append("\n");
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


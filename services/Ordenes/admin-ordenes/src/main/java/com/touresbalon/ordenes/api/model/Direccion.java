package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Contiene la información de la direccion
 */
@ApiModel(description = "Contiene la información de la direccion")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Direccion   {

  @JsonProperty("codigo")
  private Long codigo;

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }
  @JsonProperty("direccion")
  private String direccion;

  @JsonProperty("ubicacion")
  private UbicacionGeografica ubicacion;

  /**
   * Gets or Sets tipo
   */
  public enum TipoEnum {
    CASA("CASA"),
    
    OFICINA("OFICINA"),
    
    OTRO("OTRO");

    private String value;

    TipoEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TipoEnum fromValue(String value) {
      for (TipoEnum b : TipoEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("tipo")
  private TipoEnum tipo;

  @JsonProperty("pais")
  private com.touresbalon.ordenes.api.model.Pais pais;

  @JsonProperty("ciudad")
  private com.touresbalon.ordenes.api.model.Ciudad ciudad;

  @JsonProperty("estado")
  private com.touresbalon.ordenes.api.model.Estado estado;

  @JsonProperty("fechaCreacion")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaCreacion;

  public com.touresbalon.ordenes.api.model.Direccion direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * Dirección completa.
   * @return direccion
  */
  @ApiModelProperty(value = "Dirección completa.")

@Size(max=254) 
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public com.touresbalon.ordenes.api.model.Direccion ubicacion(UbicacionGeografica ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }

  /**
   * Get ubicacion
   * @return ubicacion
  */
  @ApiModelProperty(value = "")

  @Valid

  public UbicacionGeografica getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(UbicacionGeografica ubicacion) {
    this.ubicacion = ubicacion;
  }

  public com.touresbalon.ordenes.api.model.Direccion tipo(TipoEnum tipo) {
    this.tipo = tipo;
    return this;
  }

  /**
   * Get tipo
   * @return tipo
  */
  @ApiModelProperty(value = "")


  public TipoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoEnum tipo) {
    this.tipo = tipo;
  }

  public com.touresbalon.ordenes.api.model.Direccion pais(com.touresbalon.ordenes.api.model.Pais pais) {
    this.pais = pais;
    return this;
  }

  /**
   * Get pais
   * @return pais
  */
  @ApiModelProperty(value = "")

  @Valid

  public com.touresbalon.ordenes.api.model.Pais getPais() {
    return pais;
  }

  public void setPais(com.touresbalon.ordenes.api.model.Pais pais) {
    this.pais = pais;
  }

  public com.touresbalon.ordenes.api.model.Direccion ciudad(com.touresbalon.ordenes.api.model.Ciudad ciudad) {
    this.ciudad = ciudad;
    return this;
  }

  /**
   * Get ciudad
   * @return ciudad
  */
  @ApiModelProperty(value = "")

  @Valid

  public com.touresbalon.ordenes.api.model.Ciudad getCiudad() {
    return ciudad;
  }

  public void setCiudad(com.touresbalon.ordenes.api.model.Ciudad ciudad) {
    this.ciudad = ciudad;
  }

  public com.touresbalon.ordenes.api.model.Direccion estado(com.touresbalon.ordenes.api.model.Estado estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Get estado
   * @return estado
  */
  @ApiModelProperty(value = "")

  @Valid

  public com.touresbalon.ordenes.api.model.Estado getEstado() {
    return estado;
  }

  public void setEstado(com.touresbalon.ordenes.api.model.Estado estado) {
    this.estado = estado;
  }

  public com.touresbalon.ordenes.api.model.Direccion fechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Fecha de creación de la dirección
   * @return fechaCreacion
  */
  @ApiModelProperty(value = "Fecha de creación de la dirección")

  @Valid

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    com.touresbalon.ordenes.api.model.Direccion direccion = (com.touresbalon.ordenes.api.model.Direccion) o;
    return Objects.equals(this.direccion, direccion.direccion) &&
        Objects.equals(this.ubicacion, direccion.ubicacion) &&
        Objects.equals(this.tipo, direccion.tipo) &&
        Objects.equals(this.pais, direccion.pais) &&
        Objects.equals(this.ciudad, direccion.ciudad) &&
        Objects.equals(this.estado, direccion.estado) &&
        Objects.equals(this.fechaCreacion, direccion.fechaCreacion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direccion, ubicacion, tipo, pais, ciudad, estado, fechaCreacion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Direccion {\n");
    
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    ubicacion: ").append(toIndentedString(ubicacion)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
    sb.append("    ciudad: ").append(toIndentedString(ciudad)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
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


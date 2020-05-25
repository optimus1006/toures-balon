package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contiene la información de la direccion
 */
@ApiModel(description = "Contiene la información de la direccion")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Direccion   {
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
  private Pais pais;

  @JsonProperty("ciudad")
  private Ciudad ciudad;

  @JsonProperty("estado")
  private Estado estado;

  @JsonProperty("fechaCreacion")
  private OffsetDateTime fechaCreacion;

  public Direccion direccion(String direccion) {
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

  public Direccion ubicacion(UbicacionGeografica ubicacion) {
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

  public Direccion tipo(TipoEnum tipo) {
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

  public Direccion pais(Pais pais) {
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

  public Direccion ciudad(Ciudad ciudad) {
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

  public Direccion estado(Estado estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Get estado
   * @return estado
  */
  @ApiModelProperty(value = "")

  @Valid

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public Direccion fechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Fecha de creación de la dirección
   * @return fechaCreacion
  */
  @ApiModelProperty(value = "Fecha de creación de la dirección")

  @Valid

  public OffsetDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Direccion direccion = (Direccion) o;
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


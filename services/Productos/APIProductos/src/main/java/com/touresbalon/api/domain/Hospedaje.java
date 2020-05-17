package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Ciudad;
import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.domain.Cuarto;
import com.touresbalon.api.domain.Imagen;
import com.touresbalon.api.domain.TipoHospedaje;
import com.touresbalon.api.domain.UbicacionGeografica;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contiene la información del sitio donde se hospedará un cliente que pagó por una habitación en un hotel.
 */
@ApiModel(description = "Contiene la información del sitio donde se hospedará un cliente que pagó por una habitación en un hotel.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-13T20:28:44.608-05:00[America/Bogota]")

public class Hospedaje   {
  @JsonProperty("codigo")
  private Long codigo;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("tipoHospedaje")
  private TipoHospedaje tipoHospedaje;

  @JsonProperty("calificacion")
  private Integer calificacion;

  @JsonProperty("direccion")
  private String direccion;

  @JsonProperty("geolocalizacion")
  private UbicacionGeografica geolocalizacion;

  @JsonProperty("ciudad")
  private Ciudad ciudad;

  @JsonProperty("informacion")
  private String informacion;

  @JsonProperty("cantidadCuartos")
  private Integer cantidadCuartos;

  @JsonProperty("fotos")
  @Valid
  private List<Imagen> fotos = null;

  @JsonProperty("cuartos")
  @Valid
  private List<Cuarto> cuartos = null;

  @JsonProperty("convenio")
  private Convenio convenio;

  public Hospedaje codigo(Long codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Código que identifica al hospedaje
   * @return codigo
  */
  @ApiModelProperty(value = "Código que identifica al hospedaje")


  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public Hospedaje nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Nombre del establecimiento de hospedaje.
   * @return nombre
  */
  @ApiModelProperty(example = "HOTEL DUERME BUENO", value = "Nombre del establecimiento de hospedaje.")

@Size(max=255) 
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Hospedaje tipoHospedaje(TipoHospedaje tipoHospedaje) {
    this.tipoHospedaje = tipoHospedaje;
    return this;
  }

  /**
   * Get tipoHospedaje
   * @return tipoHospedaje
  */
  @ApiModelProperty(value = "")

  @Valid

  public TipoHospedaje getTipoHospedaje() {
    return tipoHospedaje;
  }

  public void setTipoHospedaje(TipoHospedaje tipoHospedaje) {
    this.tipoHospedaje = tipoHospedaje;
  }

  public Hospedaje calificacion(int calificacion) {
    this.calificacion = calificacion;
    return this;
  }

  /**
   * Calificación del hotel según los clientes y/o expertos.
   * minimum: 0
   * maximum: 5
   * @return calificacion
  */
  @ApiModelProperty(value = "Calificación del hotel según los clientes y/o expertos.")

  @Valid
@DecimalMin("0") @DecimalMax("5") 
  public int getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(int calificacion) {
    this.calificacion = calificacion;
  }

  public Hospedaje direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * Código del producto dentro del sistema.
   * @return direccion
  */
  @ApiModelProperty(example = "23TH ST - CRAWROFD - 76005", value = "Código del producto dentro del sistema.")

@Size(max=255) 
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Hospedaje geolocalizacion(UbicacionGeografica geolocalizacion) {
    this.geolocalizacion = geolocalizacion;
    return this;
  }

  /**
   * Get geolocalizacion
   * @return geolocalizacion
  */
  @ApiModelProperty(value = "")

  @Valid

  public UbicacionGeografica getGeolocalizacion() {
    return geolocalizacion;
  }

  public void setGeolocalizacion(UbicacionGeografica geolocalizacion) {
    this.geolocalizacion = geolocalizacion;
  }

  public Hospedaje ciudad(Ciudad ciudad) {
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

  public Hospedaje informacion(String informacion) {
    this.informacion = informacion;
    return this;
  }

  /**
   * Texto que contiene una descripción del sitio, sus servicios, ventajas, etc.
   * @return informacion
  */
  @ApiModelProperty(value = "Texto que contiene una descripción del sitio, sus servicios, ventajas, etc.")

@Size(max=4000) 
  public String getInformacion() {
    return informacion;
  }

  public void setInformacion(String informacion) {
    this.informacion = informacion;
  }

  public Hospedaje cantidadCuartos(Integer cantidadCuartos) {
    this.cantidadCuartos = cantidadCuartos;
    return this;
  }

  /**
   * Cantidad de cuartos disponibles en el hotel.
   * minimum: 0
   * @return cantidadCuartos
  */
  @ApiModelProperty(value = "Cantidad de cuartos disponibles en el hotel.")

@Min(0)
  public Integer getCantidadCuartos() {
    return cantidadCuartos;
  }

  public void setCantidadCuartos(Integer cantidadCuartos) {
    this.cantidadCuartos = cantidadCuartos;
  }

  public Hospedaje fotos(List<Imagen> fotos) {
    this.fotos = fotos;
    return this;
  }

  public Hospedaje addFotosItem(Imagen fotosItem) {
    if (this.fotos == null) {
      this.fotos = new ArrayList<>();
    }
    this.fotos.add(fotosItem);
    return this;
  }

  /**
   * lista de fotos que muestran las instalaciones del hospedaje.
   * @return fotos
  */
  @ApiModelProperty(value = "lista de fotos que muestran las instalaciones del hospedaje.")

  @Valid

  public List<Imagen> getFotos() {
    return fotos;
  }

  public void setFotos(List<Imagen> fotos) {
    this.fotos = fotos;
  }

  public Hospedaje cuartos(List<Cuarto> cuartos) {
    this.cuartos = cuartos;
    return this;
  }

  public Hospedaje addCuartosItem(Cuarto cuartosItem) {
    if (this.cuartos == null) {
      this.cuartos = new ArrayList<>();
    }
    this.cuartos.add(cuartosItem);
    return this;
  }

  /**
   * lista de cuartos que tienen las instalaciones del hospedaje.
   * @return cuartos
  */
  @ApiModelProperty(value = "lista de cuartos que tienen las instalaciones del hospedaje.")

  @Valid

  public List<Cuarto> getCuartos() {
    return cuartos;
  }

  public void setCuartos(List<Cuarto> cuartos) {
    this.cuartos = cuartos;
  }

  public Hospedaje convenio(Convenio convenio) {
    this.convenio = convenio;
    return this;
  }

  /**
   * Get convenio
   * @return convenio
  */
  @ApiModelProperty(value = "")

  @Valid

  public Convenio getConvenio() {
    return convenio;
  }

  public void setConvenio(Convenio convenio) {
    this.convenio = convenio;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hospedaje hospedaje = (Hospedaje) o;
    return Objects.equals(this.codigo, hospedaje.codigo) &&
        Objects.equals(this.nombre, hospedaje.nombre) &&
        Objects.equals(this.tipoHospedaje, hospedaje.tipoHospedaje) &&
        Objects.equals(this.calificacion, hospedaje.calificacion) &&
        Objects.equals(this.direccion, hospedaje.direccion) &&
        Objects.equals(this.geolocalizacion, hospedaje.geolocalizacion) &&
        Objects.equals(this.ciudad, hospedaje.ciudad) &&
        Objects.equals(this.informacion, hospedaje.informacion) &&
        Objects.equals(this.cantidadCuartos, hospedaje.cantidadCuartos) &&
        Objects.equals(this.fotos, hospedaje.fotos) &&
        Objects.equals(this.cuartos, hospedaje.cuartos) &&
        Objects.equals(this.convenio, hospedaje.convenio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, tipoHospedaje, calificacion, direccion, geolocalizacion, ciudad, informacion, cantidadCuartos, fotos, cuartos, convenio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hospedaje {\n");
    
    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    tipoHospedaje: ").append(toIndentedString(tipoHospedaje)).append("\n");
    sb.append("    calificacion: ").append(toIndentedString(calificacion)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    geolocalizacion: ").append(toIndentedString(geolocalizacion)).append("\n");
    sb.append("    ciudad: ").append(toIndentedString(ciudad)).append("\n");
    sb.append("    informacion: ").append(toIndentedString(informacion)).append("\n");
    sb.append("    cantidadCuartos: ").append(toIndentedString(cantidadCuartos)).append("\n");
    sb.append("    fotos: ").append(toIndentedString(fotos)).append("\n");
    sb.append("    cuartos: ").append(toIndentedString(cuartos)).append("\n");
    sb.append("    convenio: ").append(toIndentedString(convenio)).append("\n");
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


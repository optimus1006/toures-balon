package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Ciudad;
import com.touresbalon.api.domain.Imagen2;
import com.touresbalon.api.domain.Pais;
import com.touresbalon.api.domain.TipoHospedaje2;
import com.touresbalon.api.domain.UbicacionGeografica2;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Hospedaje2   {
  @JsonProperty("codigo")
  private Integer codigo;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("tipoHospedaje")
  private TipoHospedaje2 tipoHospedaje;

  @JsonProperty("calificacion")
  private BigDecimal calificacion;

  @JsonProperty("direccion")
  private String direccion;

  @JsonProperty("geolocalizacion")
  private UbicacionGeografica2 geolocalizacion;

  @JsonProperty("pais")
  private Pais pais;

  @JsonProperty("ciudad")
  private Ciudad ciudad;

  @JsonProperty("informacion")
  private String informacion;

  @JsonProperty("cantidadCuartos")
  private Integer cantidadCuartos;

  @JsonProperty("fotos")
  @Valid
  private List<Imagen2> fotos = null;

  public Hospedaje2 codigo(Integer codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Código que identifica al hospedaje
   * @return codigo
  */
  @ApiModelProperty(value = "Código que identifica al hospedaje")


  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public Hospedaje2 nombre(String nombre) {
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

  public Hospedaje2 tipoHospedaje(TipoHospedaje2 tipoHospedaje) {
    this.tipoHospedaje = tipoHospedaje;
    return this;
  }

  /**
   * Get tipoHospedaje
   * @return tipoHospedaje
  */
  @ApiModelProperty(value = "")

  @Valid

  public TipoHospedaje2 getTipoHospedaje() {
    return tipoHospedaje;
  }

  public void setTipoHospedaje(TipoHospedaje2 tipoHospedaje) {
    this.tipoHospedaje = tipoHospedaje;
  }

  public Hospedaje2 calificacion(BigDecimal calificacion) {
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
  public BigDecimal getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(BigDecimal calificacion) {
    this.calificacion = calificacion;
  }

  public Hospedaje2 direccion(String direccion) {
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

  public Hospedaje2 geolocalizacion(UbicacionGeografica2 geolocalizacion) {
    this.geolocalizacion = geolocalizacion;
    return this;
  }

  /**
   * Get geolocalizacion
   * @return geolocalizacion
  */
  @ApiModelProperty(value = "")

  @Valid

  public UbicacionGeografica2 getGeolocalizacion() {
    return geolocalizacion;
  }

  public void setGeolocalizacion(UbicacionGeografica2 geolocalizacion) {
    this.geolocalizacion = geolocalizacion;
  }

  public Hospedaje2 pais(Pais pais) {
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

  public Hospedaje2 ciudad(Ciudad ciudad) {
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

  public Hospedaje2 informacion(String informacion) {
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

  public Hospedaje2 cantidadCuartos(Integer cantidadCuartos) {
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

  public Hospedaje2 fotos(List<Imagen2> fotos) {
    this.fotos = fotos;
    return this;
  }

  public Hospedaje2 addFotosItem(Imagen2 fotosItem) {
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

  public List<Imagen2> getFotos() {
    return fotos;
  }

  public void setFotos(List<Imagen2> fotos) {
    this.fotos = fotos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hospedaje2 hospedaje2 = (Hospedaje2) o;
    return Objects.equals(this.codigo, hospedaje2.codigo) &&
        Objects.equals(this.nombre, hospedaje2.nombre) &&
        Objects.equals(this.tipoHospedaje, hospedaje2.tipoHospedaje) &&
        Objects.equals(this.calificacion, hospedaje2.calificacion) &&
        Objects.equals(this.direccion, hospedaje2.direccion) &&
        Objects.equals(this.geolocalizacion, hospedaje2.geolocalizacion) &&
        Objects.equals(this.pais, hospedaje2.pais) &&
        Objects.equals(this.ciudad, hospedaje2.ciudad) &&
        Objects.equals(this.informacion, hospedaje2.informacion) &&
        Objects.equals(this.cantidadCuartos, hospedaje2.cantidadCuartos) &&
        Objects.equals(this.fotos, hospedaje2.fotos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, tipoHospedaje, calificacion, direccion, geolocalizacion, pais, ciudad, informacion, cantidadCuartos, fotos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hospedaje2 {\n");
    
    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    tipoHospedaje: ").append(toIndentedString(tipoHospedaje)).append("\n");
    sb.append("    calificacion: ").append(toIndentedString(calificacion)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    geolocalizacion: ").append(toIndentedString(geolocalizacion)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
    sb.append("    ciudad: ").append(toIndentedString(ciudad)).append("\n");
    sb.append("    informacion: ").append(toIndentedString(informacion)).append("\n");
    sb.append("    cantidadCuartos: ").append(toIndentedString(cantidadCuartos)).append("\n");
    sb.append("    fotos: ").append(toIndentedString(fotos)).append("\n");
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


package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contiene la información del evento al cual es cliente asistirá.
 */
@ApiModel(description = "Contiene la información del evento al cual es cliente asistirá.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Evento   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("fecha")
  private LocalDate fecha;

  @JsonProperty("hora")
  private String hora;

  @JsonProperty("zonaHoraria")
  private String zonaHoraria;

  @JsonProperty("cantidad")
  private Integer cantidad;

  @JsonProperty("ubicacionEvento")
  private Direccion ubicacionEvento;

  @JsonProperty("localidades")
  @Valid
  private List<Localidad> localidades = null;

  @JsonProperty("asientos")
  @Valid
  private List<String> asientos = null;

  /**
   * Tipo de evento.
   */
  public enum TipoEnum {
    CONCIERTO("CONCIERTO"),
    
    FUTBOL("FUTBOL"),
    
    BALONCESTO("BALONCESTO"),
    
    ACTIVIDAD("ACTIVIDAD"),
    
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

  @JsonProperty("imagenes")
  @Valid
  private List<Imagen> imagenes = null;

  @JsonProperty("imagenPrincipal")
  private Integer imagenPrincipal = 1;

  /**
   * Estado el evento
   */
  public enum EstadoEnum {
    ACTIVO("ACTIVO"),
    
    INACTIVO("INACTIVO");

    private String value;

    EstadoEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EstadoEnum fromValue(String value) {
      for (EstadoEnum b : EstadoEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("estado")
  private EstadoEnum estado;

  public Evento id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Evento nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  */
  @ApiModelProperty(value = "")

@Size(max=100) 
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Evento descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
  */
  @ApiModelProperty(value = "")

@Size(max=255) 
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Evento fecha(LocalDate fecha) {
    this.fecha = fecha;
    return this;
  }

  /**
   * Get fecha
   * @return fecha
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public Evento hora(String hora) {
    this.hora = hora;
    return this;
  }

  /**
   * Get hora
   * @return hora
  */
  @ApiModelProperty(value = "")


  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public Evento zonaHoraria(String zonaHoraria) {
    this.zonaHoraria = zonaHoraria;
    return this;
  }

  /**
   * Get zonaHoraria
   * @return zonaHoraria
  */
  @ApiModelProperty(value = "")


  public String getZonaHoraria() {
    return zonaHoraria;
  }

  public void setZonaHoraria(String zonaHoraria) {
    this.zonaHoraria = zonaHoraria;
  }

  public Evento cantidad(Integer cantidad) {
    this.cantidad = cantidad;
    return this;
  }

  /**
   * Get cantidad
   * @return cantidad
  */
  @ApiModelProperty(value = "")


  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Evento ubicacionEvento(Direccion ubicacionEvento) {
    this.ubicacionEvento = ubicacionEvento;
    return this;
  }

  /**
   * Get ubicacionEvento
   * @return ubicacionEvento
  */
  @ApiModelProperty(value = "")

  @Valid

  public Direccion getUbicacionEvento() {
    return ubicacionEvento;
  }

  public void setUbicacionEvento(Direccion ubicacionEvento) {
    this.ubicacionEvento = ubicacionEvento;
  }

  public Evento localidades(List<Localidad> localidades) {
    this.localidades = localidades;
    return this;
  }

  public Evento addLocalidadesItem(Localidad localidadesItem) {
    if (this.localidades == null) {
      this.localidades = new ArrayList<>();
    }
    this.localidades.add(localidadesItem);
    return this;
  }

  /**
   * Get localidades
   * @return localidades
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Localidad> getLocalidades() {
    return localidades;
  }

  public void setLocalidades(List<Localidad> localidades) {
    this.localidades = localidades;
  }

  public Evento asientos(List<String> asientos) {
    this.asientos = asientos;
    return this;
  }

  public Evento addAsientosItem(String asientosItem) {
    if (this.asientos == null) {
      this.asientos = new ArrayList<>();
    }
    this.asientos.add(asientosItem);
    return this;
  }

  /**
   * Get asientos
   * @return asientos
  */
  @ApiModelProperty(value = "")


  public List<String> getAsientos() {
    return asientos;
  }

  public void setAsientos(List<String> asientos) {
    this.asientos = asientos;
  }

  public Evento tipo(TipoEnum tipo) {
    this.tipo = tipo;
    return this;
  }

  /**
   * Tipo de evento.
   * @return tipo
  */
  @ApiModelProperty(value = "Tipo de evento.")


  public TipoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoEnum tipo) {
    this.tipo = tipo;
  }

  public Evento imagenes(List<Imagen> imagenes) {
    this.imagenes = imagenes;
    return this;
  }

  public Evento addImagenesItem(Imagen imagenesItem) {
    if (this.imagenes == null) {
      this.imagenes = new ArrayList<>();
    }
    this.imagenes.add(imagenesItem);
    return this;
  }

  /**
   * Get imagenes
   * @return imagenes
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Imagen> getImagenes() {
    return imagenes;
  }

  public void setImagenes(List<Imagen> imagenes) {
    this.imagenes = imagenes;
  }

  public Evento imagenPrincipal(Integer imagenPrincipal) {
    this.imagenPrincipal = imagenPrincipal;
    return this;
  }

  /**
   * Get imagenPrincipal
   * @return imagenPrincipal
  */
  @ApiModelProperty(value = "")


  public Integer getImagenPrincipal() {
    return imagenPrincipal;
  }

  public void setImagenPrincipal(Integer imagenPrincipal) {
    this.imagenPrincipal = imagenPrincipal;
  }

  public Evento estado(EstadoEnum estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Estado el evento
   * @return estado
  */
  @ApiModelProperty(value = "Estado el evento")


  public EstadoEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoEnum estado) {
    this.estado = estado;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Evento evento = (Evento) o;
    return Objects.equals(this.id, evento.id) &&
        Objects.equals(this.nombre, evento.nombre) &&
        Objects.equals(this.descripcion, evento.descripcion) &&
        Objects.equals(this.fecha, evento.fecha) &&
        Objects.equals(this.hora, evento.hora) &&
        Objects.equals(this.zonaHoraria, evento.zonaHoraria) &&
        Objects.equals(this.cantidad, evento.cantidad) &&
        Objects.equals(this.ubicacionEvento, evento.ubicacionEvento) &&
        Objects.equals(this.localidades, evento.localidades) &&
        Objects.equals(this.asientos, evento.asientos) &&
        Objects.equals(this.tipo, evento.tipo) &&
        Objects.equals(this.imagenes, evento.imagenes) &&
        Objects.equals(this.imagenPrincipal, evento.imagenPrincipal) &&
        Objects.equals(this.estado, evento.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombre, descripcion, fecha, hora, zonaHoraria, cantidad, ubicacionEvento, localidades, asientos, tipo, imagenes, imagenPrincipal, estado);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Evento {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
    sb.append("    hora: ").append(toIndentedString(hora)).append("\n");
    sb.append("    zonaHoraria: ").append(toIndentedString(zonaHoraria)).append("\n");
    sb.append("    cantidad: ").append(toIndentedString(cantidad)).append("\n");
    sb.append("    ubicacionEvento: ").append(toIndentedString(ubicacionEvento)).append("\n");
    sb.append("    localidades: ").append(toIndentedString(localidades)).append("\n");
    sb.append("    asientos: ").append(toIndentedString(asientos)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    imagenes: ").append(toIndentedString(imagenes)).append("\n");
    sb.append("    imagenPrincipal: ").append(toIndentedString(imagenPrincipal)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
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


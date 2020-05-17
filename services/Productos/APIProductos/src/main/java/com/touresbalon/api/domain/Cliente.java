package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Categoria;
import com.touresbalon.api.domain.Direccion;
import com.touresbalon.api.domain.Estado;
import com.touresbalon.api.domain.Tarjeta;
import com.touresbalon.api.domain.TipoIdentificacion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Cliente
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Cliente   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("identificacion")
  private String identificacion;

  @JsonProperty("nombres")
  private String nombres;

  @JsonProperty("apellidos")
  private String apellidos;

  @JsonProperty("email")
  private String email;

  @JsonProperty("telefono")
  private String telefono;

  @JsonProperty("celular")
  private String celular;

  @JsonProperty("estado")
  private Estado estado;

  @JsonProperty("categoria")
  private Categoria categoria;

  @JsonProperty("tipoIdentificacion")
  private TipoIdentificacion tipoIdentificacion;

  @JsonProperty("direcciones")
  @Valid
  private List<Direccion> direcciones = null;

  @JsonProperty("tarjetas")
  @Valid
  private List<Tarjeta> tarjetas = null;

  public Cliente id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente identificacion(String identificacion) {
    this.identificacion = identificacion;
    return this;
  }

  /**
   * Get identificacion
   * @return identificacion
  */
  @ApiModelProperty(value = "")


  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public Cliente nombres(String nombres) {
    this.nombres = nombres;
    return this;
  }

  /**
   * Nombres del cliente.
   * @return nombres
  */
  @ApiModelProperty(value = "Nombres del cliente.")


  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public Cliente apellidos(String apellidos) {
    this.apellidos = apellidos;
    return this;
  }

  /**
   * Apellidos.
   * @return apellidos
  */
  @ApiModelProperty(value = "Apellidos.")


  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public Cliente email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Cliente telefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  /**
   * Get telefono
   * @return telefono
  */
  @ApiModelProperty(value = "")


  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Cliente celular(String celular) {
    this.celular = celular;
    return this;
  }

  /**
   * Get celular
   * @return celular
  */
  @ApiModelProperty(value = "")


  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public Cliente estado(Estado estado) {
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

  public Cliente categoria(Categoria categoria) {
    this.categoria = categoria;
    return this;
  }

  /**
   * Get categoria
   * @return categoria
  */
  @ApiModelProperty(value = "")

  @Valid

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Cliente tipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
    return this;
  }

  /**
   * Get tipoIdentificacion
   * @return tipoIdentificacion
  */
  @ApiModelProperty(value = "")

  @Valid

  public TipoIdentificacion getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }

  public Cliente direcciones(List<Direccion> direcciones) {
    this.direcciones = direcciones;
    return this;
  }

  public Cliente addDireccionesItem(Direccion direccionesItem) {
    if (this.direcciones == null) {
      this.direcciones = new ArrayList<>();
    }
    this.direcciones.add(direccionesItem);
    return this;
  }

  /**
   * Get direcciones
   * @return direcciones
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Direccion> getDirecciones() {
    return direcciones;
  }

  public void setDirecciones(List<Direccion> direcciones) {
    this.direcciones = direcciones;
  }

  public Cliente tarjetas(List<Tarjeta> tarjetas) {
    this.tarjetas = tarjetas;
    return this;
  }

  public Cliente addTarjetasItem(Tarjeta tarjetasItem) {
    if (this.tarjetas == null) {
      this.tarjetas = new ArrayList<>();
    }
    this.tarjetas.add(tarjetasItem);
    return this;
  }

  /**
   * Get tarjetas
   * @return tarjetas
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Tarjeta> getTarjetas() {
    return tarjetas;
  }

  public void setTarjetas(List<Tarjeta> tarjetas) {
    this.tarjetas = tarjetas;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cliente cliente = (Cliente) o;
    return Objects.equals(this.id, cliente.id) &&
        Objects.equals(this.identificacion, cliente.identificacion) &&
        Objects.equals(this.nombres, cliente.nombres) &&
        Objects.equals(this.apellidos, cliente.apellidos) &&
        Objects.equals(this.email, cliente.email) &&
        Objects.equals(this.telefono, cliente.telefono) &&
        Objects.equals(this.celular, cliente.celular) &&
        Objects.equals(this.estado, cliente.estado) &&
        Objects.equals(this.categoria, cliente.categoria) &&
        Objects.equals(this.tipoIdentificacion, cliente.tipoIdentificacion) &&
        Objects.equals(this.direcciones, cliente.direcciones) &&
        Objects.equals(this.tarjetas, cliente.tarjetas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identificacion, nombres, apellidos, email, telefono, celular, estado, categoria, tipoIdentificacion, direcciones, tarjetas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cliente {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identificacion: ").append(toIndentedString(identificacion)).append("\n");
    sb.append("    nombres: ").append(toIndentedString(nombres)).append("\n");
    sb.append("    apellidos: ").append(toIndentedString(apellidos)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    celular: ").append(toIndentedString(celular)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    tipoIdentificacion: ").append(toIndentedString(tipoIdentificacion)).append("\n");
    sb.append("    direcciones: ").append(toIndentedString(direcciones)).append("\n");
    sb.append("    tarjetas: ").append(toIndentedString(tarjetas)).append("\n");
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


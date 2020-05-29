package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * TipoTransporte
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class TipoTransporte   {
  @JsonProperty("id")
  private int id;

  @JsonProperty("description")
  private String description;

  @JsonProperty("capacidadPersonas")
  private BigDecimal capacidadPersonas;

  @JsonProperty("capacidadEquipaje")
  private BigDecimal capacidadEquipaje;

  /**
   * 'Capacidad:'   * Kg: Kilogramos   * Ton: Toneladas 
   */
  public enum TipoCapacidadCargaEnum {
    KG("KG"),
    
    TON("TON");

    private String value;

    TipoCapacidadCargaEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TipoCapacidadCargaEnum fromValue(String value) {
      for (TipoCapacidadCargaEnum b : TipoCapacidadCargaEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("tipoCapacidadCarga")
  private TipoCapacidadCargaEnum tipoCapacidadCarga;

  public TipoTransporte id(int id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public TipoTransporte description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TipoTransporte capacidadPersonas(BigDecimal capacidadPersonas) {
    this.capacidadPersonas = capacidadPersonas;
    return this;
  }

  /**
   * Get capacidadPersonas
   * @return capacidadPersonas
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getCapacidadPersonas() {
    return capacidadPersonas;
  }

  public void setCapacidadPersonas(BigDecimal capacidadPersonas) {
    this.capacidadPersonas = capacidadPersonas;
  }

  public TipoTransporte capacidadEquipaje(BigDecimal capacidadEquipaje) {
    this.capacidadEquipaje = capacidadEquipaje;
    return this;
  }

  /**
   * Get capacidadEquipaje
   * @return capacidadEquipaje
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getCapacidadEquipaje() {
    return capacidadEquipaje;
  }

  public void setCapacidadEquipaje(BigDecimal capacidadEquipaje) {
    this.capacidadEquipaje = capacidadEquipaje;
  }

  public TipoTransporte tipoCapacidadCarga(TipoCapacidadCargaEnum tipoCapacidadCarga) {
    this.tipoCapacidadCarga = tipoCapacidadCarga;
    return this;
  }

  /**
   * 'Capacidad:'   * Kg: Kilogramos   * Ton: Toneladas 
   * @return tipoCapacidadCarga
  */
  @ApiModelProperty(value = "'Capacidad:'   * Kg: Kilogramos   * Ton: Toneladas ")


  public TipoCapacidadCargaEnum getTipoCapacidadCarga() {
    return tipoCapacidadCarga;
  }

  public void setTipoCapacidadCarga(TipoCapacidadCargaEnum tipoCapacidadCarga) {
    this.tipoCapacidadCarga = tipoCapacidadCarga;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TipoTransporte tipoTransporte = (TipoTransporte) o;
    return Objects.equals(this.id, tipoTransporte.id) &&
        Objects.equals(this.description, tipoTransporte.description) &&
        Objects.equals(this.capacidadPersonas, tipoTransporte.capacidadPersonas) &&
        Objects.equals(this.capacidadEquipaje, tipoTransporte.capacidadEquipaje) &&
        Objects.equals(this.tipoCapacidadCarga, tipoTransporte.tipoCapacidadCarga);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, capacidadPersonas, capacidadEquipaje, tipoCapacidadCarga);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoTransporte {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    capacidadPersonas: ").append(toIndentedString(capacidadPersonas)).append("\n");
    sb.append("    capacidadEquipaje: ").append(toIndentedString(capacidadEquipaje)).append("\n");
    sb.append("    tipoCapacidadCarga: ").append(toIndentedString(tipoCapacidadCarga)).append("\n");
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


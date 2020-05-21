package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Información requerida apra mostrar un código de barras en una factura.
 */
@ApiModel(description = "Información requerida apra mostrar un código de barras en una factura.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-16T23:29:26.784Z[GMT]")
public class CodigoBarras   {
  @JsonProperty("tipo")
  private String tipo = null;

  @JsonProperty("texto")
  private String texto = null;

  public CodigoBarras tipo(String tipo) {
    this.tipo = tipo;
    return this;
  }

  /**
   * The type of the barcode; possible values are AZTEC, CODABAR, CODE_39, CODE_93, CODE_128, DATA_MATRIX, EAN_8, EAN_13, ITF, MAXICODE, PDF_417, QR_CODE, RSS_14, RSS_EXPANDED, UPC_A, UPC_E, All_1D, UPC_EAN_EXTENSION, MSI, PLESSEY, IMB\",
   * @return tipo
  **/
  @ApiModelProperty(value = "The type of the barcode; possible values are AZTEC, CODABAR, CODE_39, CODE_93, CODE_128, DATA_MATRIX, EAN_8, EAN_13, ITF, MAXICODE, PDF_417, QR_CODE, RSS_14, RSS_EXPANDED, UPC_A, UPC_E, All_1D, UPC_EAN_EXTENSION, MSI, PLESSEY, IMB\",")
  
    public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public CodigoBarras texto(String texto) {
    this.texto = texto;
    return this;
  }

  /**
   * Texto con la información del código de barras.
   * @return texto
  **/
  @ApiModelProperty(value = "Texto con la información del código de barras.")
  
    public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodigoBarras codigoBarras = (CodigoBarras) o;
    return Objects.equals(this.tipo, codigoBarras.tipo) &&
        Objects.equals(this.texto, codigoBarras.texto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipo, texto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodigoBarras {\n");
    
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    texto: ").append(toIndentedString(texto)).append("\n");
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

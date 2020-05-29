package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Moneda
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T05:49:11.682Z[GMT]")
public class Moneda   {
  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("currency")
  private String currency = null;

  public Moneda amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Cantidad expresada como número decimal para la mayoría de monedas del mundo.
   * @return amount
  **/
  @ApiModelProperty(example = "99.95", required = true, value = "Cantidad expresada como número decimal para la mayoría de monedas del mundo.")
      @NotNull

    @Valid
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Moneda currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Código de la moneda de 3 letras, definido en el estándar ISO-4217
   * @return currency
  **/
  @ApiModelProperty(example = "EUR", required = true, value = "Código de la moneda de 3 letras, definido en el estándar ISO-4217")
      @NotNull

    public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Moneda moneda = (Moneda) o;
    return Objects.equals(this.amount, moneda.amount) &&
        Objects.equals(this.currency, moneda.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Moneda {\n");

    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

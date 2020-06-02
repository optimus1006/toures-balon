package com.touresbalon.ordenes.restclient.convenios.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.api.domain.Convenio;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Respuesta a la consulta de un convenio por Id
 */
@ApiModel(description = "Respuesta a la consulta de un convenio por Id")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-29T18:25:30.142-04:12[America/Bogota]")
public class ConveniosGETByIdRs {
    @JsonProperty("convenio")
    private Convenio convenio = null;

    public ConveniosGETByIdRs convenio(Convenio convenio){
        this.convenio = convenio;
        return this;
    }

    /**
     * Get convenio
     * @return convenio
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConveniosGETByIdRs conveniosGETByIdRs = (ConveniosGETByIdRs) o;
        return Objects.equals(this.convenio, conveniosGETByIdRs.convenio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convenio);
    }

    /**
     * Converts to string the whole object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConveniosGETByIdRs {\n");

        sb.append("    convenio: ").append(toIndentedString(convenio)).append("\n");
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

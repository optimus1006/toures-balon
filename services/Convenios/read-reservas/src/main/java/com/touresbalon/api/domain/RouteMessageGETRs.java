package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.Objects;

@ApiModel(description = "Respuesta a la consulta de un mensaje de ruteo")
public class RouteMessageGETRs {

    @JsonProperty("aprobacionReserva")
    AprobacionReserva aprobacionReserva;

    public RouteMessageGETRs aprobacionReserva(AprobacionReserva aprobacionReserva){
        this.aprobacionReserva = aprobacionReserva;
        return this;
    }

    public AprobacionReserva getAprobacionReserva() {
        return aprobacionReserva;
    }

    public void setAprobacionReserva(AprobacionReserva aprobacionReserva) {
        this.aprobacionReserva = aprobacionReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteMessageGETRs routeMessageGETRs = (RouteMessageGETRs) o;
        return Objects.equals(this.aprobacionReserva, routeMessageGETRs.aprobacionReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aprobacionReserva);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RouteMessageGETRs {\n");
        sb.append("    aprobacionReserva: ").append(toIndentedString(aprobacionReserva)).append("\n");
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

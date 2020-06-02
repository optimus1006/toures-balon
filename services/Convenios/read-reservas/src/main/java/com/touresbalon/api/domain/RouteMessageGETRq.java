package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.kafka.ConvenioMessage;
import com.touresbalon.kafka.ReservaMessage;
import io.swagger.annotations.ApiModel;

import java.util.Objects;

@ApiModel(description = "Respuesta a una reserva")
public class RouteMessageGETRq {

    @JsonProperty("reservaMessage")
    private ReservaMessage reservaMessage;

    @JsonProperty("convenioMessage")
    private ConvenioMessage convenioMessage;

    public RouteMessageGETRq routeMessage (ReservaMessage reservaMessage, ConvenioMessage convenioMessage){
        this.convenioMessage = convenioMessage;
        this.reservaMessage = reservaMessage;
        return this;
    }

    public ReservaMessage getReservaMessage() {
        return reservaMessage;
    }

    public void setReservaMessage(ReservaMessage reservaMessage) {
        this.reservaMessage = reservaMessage;
    }

    public ConvenioMessage getConvenioMessage() {
        return convenioMessage;
    }

    public void setConvenioMessage(ConvenioMessage convenioMessage) {
        this.convenioMessage = convenioMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteMessageGETRq routeMessageGETRq = (RouteMessageGETRq) o;
        return Objects.equals(this.convenioMessage, routeMessageGETRq.convenioMessage) && Objects.equals(this.reservaMessage, routeMessageGETRq.reservaMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservaMessage, convenioMessage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProductosPSTRq {\n");
        sb.append("    reservaMessage: ").append(toIndentedString(reservaMessage)).append("\n");
        sb.append("    convenioMessage: ").append(toIndentedString(convenioMessage)).append("\n");
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

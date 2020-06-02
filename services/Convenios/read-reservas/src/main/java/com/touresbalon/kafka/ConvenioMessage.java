package com.touresbalon.kafka;

import com.touresbalon.api.domain.Convenio;

import java.util.Objects;

public class ConvenioMessage {

    private String endpoint;
    private String templateEntrada;
    private String templateSalida;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTemplateEntrada() {
        return templateEntrada;
    }

    public void setTemplateEntrada(String templateEntrada) {
        this.templateEntrada = templateEntrada;
    }

    public String getTemplateSalida() {
        return templateSalida;
    }

    public void setTemplateSalida(String templateSalida) {
        this.templateSalida = templateSalida;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConvenioMessage {\n");
        sb.append("    endpoint: ").append(toIndentedString(getEndpoint())).append("\n");
        sb.append("    templateEntrada: ").append(toIndentedString(getTemplateEntrada())).append("\n");
        sb.append("    templateSalida: ").append(toIndentedString(getTemplateSalida())).append("\n");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConvenioMessage convenioMessage = (ConvenioMessage) o;
        return Objects.equals(this.endpoint, convenioMessage.endpoint) &&
                Objects.equals(this.templateEntrada, convenioMessage.templateEntrada) &&
                Objects.equals(this.templateSalida, convenioMessage.templateSalida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint, templateEntrada, templateSalida);
    }
}

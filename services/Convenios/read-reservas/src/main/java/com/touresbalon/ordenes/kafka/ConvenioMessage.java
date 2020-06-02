package com.touresbalon.ordenes.kafka;

import com.touresbalon.api.domain.Convenio;

public class ConvenioMessage extends Convenio {

    private String endpoint;
    private String templateEntrada;
    private String templateSalida;

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getTemplateEntrada() {
        return templateEntrada;
    }

    @Override
    public void setTemplateEntrada(String templateEntrada) {
        this.templateEntrada = templateEntrada;
    }

    @Override
    public String getTemplateSalida() {
        return templateSalida;
    }

    @Override
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
}

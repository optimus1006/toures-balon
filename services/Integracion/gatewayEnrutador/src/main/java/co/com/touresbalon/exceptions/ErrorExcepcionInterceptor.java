/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.touresbalon.exceptions;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Andrea Gómez
 *
 */
public class ErrorExcepcionInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Log log = LogFactory.getLog(ErrorExcepcionInterceptor.class);

    public ErrorExcepcionInterceptor() {
        super(Phase.USER_STREAM);
    }

    /**
     *
     * @param message
     * @throws Fault
     */
    @Override
    public void handleMessage(Message message) throws Fault {
        if (message.getExchange().getOutMessage().get("Content-Type") == null) {

            //log.info(message.getExchange().getInMessage());            
            //log.info(message.getExchange().getOutMessage());            
            String uri = (String) message.getExchange().getInMessage().get("org.apache.cxf.message.Message.PATH_INFO");
            String[] paths = uri.split("/");
            Integer responseCode = (Integer) message.getExchange().getOutMessage().get("org.apache.cxf.message.Message.RESPONSE_CODE");
            HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
                    .get(AbstractHTTPDestination.HTTP_RESPONSE);
            response.setContentType("application/json");
            String salida;
            String descripcion;
            String codigo;
            if (null == responseCode) {
                responseCode = 500;
                descripcion = "Error interno del servicio";
                codigo = "500";
            } else switch (responseCode) {
                case 404:
                    descripcion = "operacion no encontrada: " + paths[paths.length - 1];
                    codigo = "404";
                    break;
                case 405:
                    descripcion = "Metodo no permitido para la operacion: " + message.getExchange().getInMessage().get("org.apache.cxf.request.method");
                    codigo = "405";
                    break;
                default:
                    responseCode = 500;
                    descripcion = "Error interno del servicio";
                    codigo = "500";
                    break;
            }

            response.setStatus(responseCode);
            salida = "{\"codigoError\": \"" + codigo + "\", \"descripcionError\": \"" + descripcion + "\"}";

            try {
                response.getOutputStream().write(salida.getBytes());
                response.getOutputStream().flush();
            } catch (IOException iex) {
            }

            message.getInterceptorChain().abort();
        }
    }
}

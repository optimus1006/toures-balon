package co.com.touresbalon.processor;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import co.com.touresbalon.model.RespuestaError;

/**
 * 
 * @author Andrea Gómez
 *
 */
public class RespuestaErrorProcessor {

    static final String BEAN_VALIDATION = "BeanValidationException";
    static final String IVALID_PAYLOAD = "InvalidPayloadException";    
    static final String CONNECTION = "CannotGetJdbcConnectionException";
    static final String TIMEOUT = "SocketTimeoutException";
    static final String SQL_CONNECT = "SQLRecoverableException";


    private static Log log = LogFactory.getLog(RespuestaErrorProcessor.class);

    /**
     * 
     * @param exchange
     * @throws Exception
     */
    public void process(Exchange exchange) throws Exception {

        String codigoHttp = "";
        String codigoRespuesta = null;
        String descriptionRespuesta = "";
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        if (exception.getClass().getSimpleName().equals(BEAN_VALIDATION)) {
            try {

                BeanValidationException beanException = (BeanValidationException) exception;

                codigoHttp = exchange.getProperty("codigohttp").toString();
                codigoRespuesta = exchange.getProperty("codigo").toString();
                descriptionRespuesta = exchange.getProperty("descripcion").toString() + ": ";

                for (ConstraintViolation<Object> constraintViolation : beanException.getConstraintViolations()) {
                    descriptionRespuesta += constraintViolation.getPropertyPath() + " => "
                            + constraintViolation.getMessage() + ", ";
                }
            } catch (Exception e) {
                descriptionRespuesta = "No description error";
            }
        }  else if (exception.getClass().getSimpleName().equals(CONNECTION) || exception.getClass().getSimpleName().equals(SQL_CONNECT)) {
            
            codigoHttp = exchange.getProperty("codigohttp").toString();
            codigoRespuesta = exchange.getProperty("codigo").toString();
            descriptionRespuesta = exchange.getProperty("descripcion").toString();
            
            CannotGetJdbcConnectionException connectionException = (CannotGetJdbcConnectionException) exception;
            //descriptionRespuesta += connectionException.getMessage();
            if (getRootCause(connectionException).getClass().getSimpleName().equals(TIMEOUT)) {
                //SocketTimeoutException connectionExceptionTimeOut = (SocketTimeoutException) getRootCause(connectionException);
                codigoRespuesta = exchange.getProperty("codigoTimeout").toString();
                descriptionRespuesta = exchange.getProperty("descripcionTimeout").toString();
                //descriptionRespuesta += connectionExceptionTimeOut.getMessage();
            }            
        } else {
            codigoHttp = exchange.getProperty("codigohttp").toString();
            codigoRespuesta = exchange.getProperty("codigo").toString();
            descriptionRespuesta = exchange.getProperty("descripcion").toString();
            //Exception excepcion = (Exception) exception;            
            //descriptionRespuesta += excepcion.getMessage();            
        }

        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigoError(codigoRespuesta);
        respuestaError.setDescripcionError(descriptionRespuesta);

        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, codigoHttp);
        exchange.getIn().setBody(Response.status(Integer.parseInt(codigoHttp)).entity(respuestaError).build());
        exchange.getOut().setBody(Response.status(Integer.parseInt(codigoHttp)).entity(respuestaError).build());

        exchange.setProperty("codigoError", respuestaError.getCodigoError());
        exchange.setProperty("descripcionError", respuestaError.getDescripcionError());
    }

    private Exception getRootCause(Exception excepcion) {
        if (excepcion.getCause() != null) {
            return getRootCause((Exception) excepcion.getCause());
        } else {
            return excepcion;
        }
    }

}

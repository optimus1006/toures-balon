package com.touresbalon.ordenes.api;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name="widget", description="Widget operations."),
                @Tag(name="gasket", description="Operations related to orders")
        },
        info = @Info(
                title="Ordenes de Pago API",
                version = "1.0.0",
                description = "Este es el API de ordenes para el grupo de servicios de Toures Balon. Este documentio contiene la definición de los servicios requeridos para la gestión de ordenes de pago de un producto creado por el cliente.",
                contact = @Contact(
                        name = "Archetype",
                        url = "http://exampleurl.com/contact",
                        email = "iv.pinilla@javeriana.edu.co"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/optimus1006/toures-balon/blob/master/README.md"))
)
public class SwaggerDocumentationConfig extends Application {
}

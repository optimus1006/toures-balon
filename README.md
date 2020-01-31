# toures-balon

## Integrantes

 - Jennifer Goyeneche
 - Andrea Gomez
 - Manuel Gonzalez 
 - Ivan Linares 
 - Ivan Pinilla 
 - Julio Pinzón

## Visión general

Esta aplicación expone varias operaciones que permiten realizar la compra de planes de turismo especializado en eventos deportivos para la empresa Toures Balon

## Tecnologías

Hecha con Spring Boot
API Gateway: 
Contenerización: 
Patrones usados: Composite (Orquestación), API Gateway, Microservicios.

## API REST

En este link se encuentra toda la información del API expuesto para el consumo de las aplicaciones del cliente: 

[https://app.swaggerhub.com/apis/ujaveriana/sistema_pagos/1.0.0#trial](https://app.swaggerhub.com/apis/ujaveriana/sistema_pagos/1.0.0#trial)

## Operaciones

### Ordenes

Contiene 2 operaciones con las que se puede operar sobre la cuenta: 

- `/cuenta` : Consulta al información de la cuenta a la que se va a debitar el valor del pago de la factura ingresada. *(No implementado)*
- `/cuenta/procesar`: Debita el valor del pago de la factura. *(No implementado)*

### Clientes

Contiene las operaciones necesarias para poder realizar el pago de una factura por medio del número de referencia.

 - `/factura`: Consulta la factura para obtener el monto a cancelar.
 -  `/factura/pagar`: Permite pagar una factura por el valor de la misma.
 -  `/factura/compensar`: Permite reversar un pago de una factura.

### Productos

Contiene las operaciones necesarias para poder realizar el pago de una factura por medio del número de referencia.

 - `/factura`: Consulta la factura para obtener el monto a cancelar.
 -  `/factura/pagar`: Permite pagar una factura por el valor de la misma.
 -  `/factura/compensar`: Permite reversar un pago de una factura.

### Proveedores

Contiene las operaciones necesarias para poder realizar el pago de una factura por medio del número de referencia.

 - `/factura`: Consulta la factura para obtener el monto a cancelar.
 -  `/factura/pagar`: Permite pagar una factura por el valor de la misma.
 -  `/factura/compensar`: Permite reversar un pago de una factura.

## Documentación del API

### API expuesto en el API Gateway:

Se puede ver la documentación por medio de la interfaz de swagger-ui by en la siguiente dirección:  
[http://localhost:8080/TouresBalon/v1.0.0/](http://localhost:8080/TouresBalon/v1.0.0/)

En el archivo **application.properties** pueden verse las propiedades de contexto, documentación y puerto en donde se despliega la aplicación, pueden ser cambiados desde ahí.

### API de las ordenes:

|Operación|Campo|Cardinalidad|Tipo|descripción|
|--|--|--|--|--|
|servicio|numeroFactura|1..1|Long|Identificador de la factura|
|servicio|operacion|1..1|String|Operaciones de consulta o pago|

### Diagramas de implementación


### Diagrama de proceso de negocio propuesto (BPM)

![TouresBalon](https://github.com/optimus1006/toures-balon/blob/master/diagrams/TouresBalonPago.png)

### Patrones utilizados

- `Api gateway
- `Composicion por orquestacion
- `Microservicios

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTU1Mzk5NDgsLTY1MzA0NzU5MCwzMzE5NT
U5NDMsLTE5NDIzNjY5NDAsLTE2NzA3NDYxMjIsNzk2OTI1Nzk4
LC0zNzYxMjM5ODYsLTcwNDc5MTUyNyw5MDcxMDk2MjhdfQ==
-->

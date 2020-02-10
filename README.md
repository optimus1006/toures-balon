# Toures-Balon

## Integrantes

 - Jennifer Goyeneche
 - Andrea Gomez
 - Manuel González 
 - Iván Linares 
 - Iván Pinilla 
 - Julio Pinzón

## Visión general

Esta aplicación expone varias operaciones que permiten realizar la compra de planes de turismo especializado en eventos deportivos para la empresa Toures Balon

### Diagrama de proceso de negocio propuesto (BPM)

![TouresBalon](https://github.com/optimus1006/toures-balon/blob/master/diagrams/TouresBalonPago.png)

## Catálogo de servicios

DOMINIO | SUBDOMINIO | SERVICIO | DESCRIPCIÓN | CANAL
--- | --- | --- | --- | --- 
Autenticación | Servicios de autenticación | Generación de autenticación tanto para aplicaciones como para usuarios | Web, Movil
Clientes |  | Registrar Clientes | Registro del cliente | Web, Movil
Clientes |  | Actualizar Clientes | Actualizacion de datos y estado de clientes | Web, Movil
Clientes |  | Consultar Clientes | Consulta de clientes por los diferentes parametros | Web, Movil
Ordenes |  | Crear ordenes | Crear ordenes de productos | Web, Movil
Órdenes |  | Cancelación de Órdenes | El sistema permitirá la cancelación de órdenes de pedido en validación o en reservación que maneja TouresBalón. | Web, Movil
Órdenes |  | Consultar Ordenes | detalle de productos se debe mostrar | Web, Movil
Órdenes |  | Pagar orden | Servicio de pago de ordenes | Web, Movil
Órdenes | Factura | Consultar factura | Servicio de consulta de factura de una orden | Web, Movil
Productos |  | Administración de Productos | El sistema permitirá la creación, modificación y eliminación de los productos que comercializa TouresBalón. (Incluye la administración de imágenes). También el sistema permitirá la creación(*), modificación(*) y eliminación(*) de las tarifas asociadas a los productos | Web, Movil
Productos | Campañas | Administrar Campañas | El sistema permitirá creación(*), modificación(*) y eliminación(*) de campañas. Una campaña es una imagen promocional que se mostrará en el sitio Web y está asociada a un producto durante un periodo de tiempo dado. | Web, Movil
Productos | Paquetes |  Administrar Paquetes | El sistema permitirá creación(*), modificación(*) y eliminación(*) de paquetes. Un paquete es un conjuto de eventos, con transportes y hospedajes | Web, Movil
Productos | Transportes |  Administrar Transportes | El sistema permitirá creación(*), modificación(*) y eliminación(*) de paquetes. Un paquete es un conjuto de eventos, con transportes y hospedajes | Web, Movil
Productos | Hospedajes |  Administrar Hospedajes | El sistema permitirá creación(*), modificación(*) y eliminación(*) de hospedajes. | Web
Productos | Eventos |  Administrar Productos | El sistema permitirá creación(*), modificación(*) y eliminación(*) de productos. | Web, Movil
Convenios |  | Administración de convenios | Consulta y enrolamiento de proveedores/convenios/alianzas  | Web

## Operaciones

### Ordenes

Contiene 4 operaciones con las que se puede operar sobre la orden: 

- `/ordenes` [GET]: Consulta al información de las ordenes basado en parámetros de búsqueda *(No implementado)*
- `/ordenes` [POST]: Crea la orden de acuerdo con los parámetros recibidos. *(No implementado)*
- `/ordenes/{id}` [PATCH]: Actualizar la orden de acuerdo con los parámetros recibidos. *(No implementado)*
- `/ordenes/{id}` [DELETE]: Cancela la orden. *(No implementado)*
- `/ordenes/pagar` [POST]: Pagar la orden. *(No implementado)*
- `/ordenes/factura` [GET]: Consultar factura. *(No implementado)*

### Clientes

Contiene las operaciones necesarias de los clientes

- `/clientes` [GET]: Consulta al información de los clientes basado en parámetros de búsqueda *(No implementado)*
- `/clientes` [POST]: Crea el cliente de acuerdo con los parámetros recibidos. *(No implementado)*
- `/clientes/{id}` [PATCH]: Actualizar el cliente de acuerdo con los parámetros recibidos. *(No implementado)*
- `/clientes/{id}` [DELETE]: Borrar el cliente de acuerdo al id recibido *(No implementado)*

### Productos

Contiene las operaciones sobre los productos.

- `/productos` [GET]: Consulta al información de los productos basado en parámetros de búsqueda *(No implementado)*
- `/productos` [POST]: Crea el producto de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/{id}` [PATCH]: Actualizar el producto de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/{id}` [DELETE]: Eliminar el producto. *(No implementado)*
- `/productos/campanias` [GET]: Consulta al información de las campañas basado en parámetros de busqueda *(No implementado)*
- `/productos/campanias` [POST]: Crea la campaña de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/campanias/{id}` [PUT]: Actualizar la campaña de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/campanias/{id}` [DELETE]: Eliminar la campaña. *(No implementado)*
- `/productos/paquetes` [GET]: Consulta al información de las campañas basado en parámetros de busqueda *(No implementado)*
- `/productos/paquetes` [POST]: Crea la campaña de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/paquetes/{id}` [PUT]: Actualizar la campaña de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/paquetes/{id}` [DELETE]: Eliminar la campaña. *(No implementado)*
- `/productos/paquetes/itinerario` [GET]: Consulta al información del itinerario asociado al paquete *(No implementado)*
- `/productos/transportes` [GET]: Consulta al información de las transportes basado en parámetros de busqueda *(No implementado)*
- `/productos/transportes` [POST]: Crea el transporte de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/transportes/{id}` [PUT]: Actualizar el transporte de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/transportes/{id}` [DELETE]: Eliminar el transporte. *(No implementado)*
- `/productos/hospedajes` [GET]: Consulta al información de las hospedajes basado en parámetros de busqueda *(No implementado)*
- `/productos/hospedajes` [POST]: Crea el hospedaje de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/hospedajes/{id}` [PUT]: Actualizar el hospedaje de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/hospedajes/{id}` [DELETE]: Eliminar el hospedaje. *(No implementado)*
- `/productos/eventos` [GET]: Consulta al información de las eventos basado en parámetros de busqueda *(No implementado)*
- `/productos/eventos` [POST]: Crea el evento de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/eventos/{id}` [PUT]: Actualizar el evento de acuerdo con los parámetros recibidos. *(No implementado)*
- `/productos/eventos/{id}` [DELETE]: Eliminar el evento. *(No implementado)*

### Convenios

Contiene las operaciones sobre los proveedores/convenios/alianzas.

- `/convenios` [GET]: Consulta al información de los convenios basado en parámetros de búsqueda *(No implementado)*
- `/convenios` [POST]: Crea el convenio de acuerdo con los parámetros recibidos. *(No implementado)*
- `/convenios/{id}` [PATCH]: Actualizar el convenio de acuerdo con los parámetros recibidos. *(No implementado)*
- `/convenios/{id}` [DELETE]: Eliminar el convenio. *(No implementado)*

## Tecnologías

Hecha con Spring Boot
API Gateway: 
Contenerización: 
Patrones usados: Composite (Orquestación), API Gateway, Microservicios.

## API REST

En este link se encuentra toda la información del API expuesto para el consumo de las aplicaciones del cliente: 

[API Swagger](https://app.swaggerhub.com/apis/ujaveriana/sistema_pagos/1.0.0#free)

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

### Patrones utilizados

- `Api gateway
- `Composición por orquestación
- `Microservicios

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTU1Mzk5NDgsLTY1MzA0NzU5MCwzMzE5NT
U5NDMsLTE5NDIzNjY5NDAsLTE2NzA3NDYxMjIsNzk2OTI1Nzk4
LC0zNzYxMjM5ODYsLTcwNDc5MTUyNyw5MDcxMDk2MjhdfQ==
-->

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

DOMINIO | SERVICIO | DESCRIPCIÓN
--- | --- | --- 
Autenticación | Servicios de autenticación | Generación de autenticación tanto para aplicaciones como para usuarios
Clientes | Administración de Clientes | Los clientes podrán ingresar al sitio e inscribirse ingresando sus datos principales (ver modelo de datos y exceptuar “status”); así mismo, el usuario deberá ingresar su contraseña
Facturación | Conciliar Movimientos | Servicio que controla la conciliación de movimientos contables con el sistema financiero
Ordenes | Crear ordenes | Crear ordenes de productos
Órdenes | Cancelación de Órdenes | El sistema permitirá la cancelación de órdenes de pedido en validación o en reservación que maneja TouresBalón.
Órdenes | Consultar Ordenes | detalle de productos se debe mostrar
Órdenes | Pagar orden | Servicio de pago de ordenes
Órdenes | Consultar factura | Servicio de consulta de factura de una orden
Productos | Administración de Productos | El sistema permitirá la creación, modificación y eliminación de los productos que comercializa TouresBalón. (Incluye la administración de imágenes). También el sistema permitirá la creación(*), modificación(*) y eliminación(*) de las tarifas asociadas a los productos
Productos | Administrar Campañas | El sistema permitirá creación(*), modificación(*) y eliminación(*) de campañas. Una campaña es una imagen promocional que se mostrará en el sitio Web y está asociada a un producto durante un periodo de tiempo dado.
Convenios | Administración de convenios | Consulta y enrolamiento de proveedores/convenios/alianzas 

## Operaciones

### Ordenes

Contiene 4 operaciones con las que se puede operar sobre la orden: 

- `/ordenes` [GET]: Consulta al información de las ordenes basado en parámetros de búsqueda *(No implementado)*
- `/orden` [POST]: Crea la orden de acuerdo con los parámetros recibidos. *(No implementado)*
- `/orden` [PUT]: Actualizar la orden de acuerdo con los parámetros recibidos. *(No implementado)*
- `/orden` [DELETE]: Cancela la orden. *(No implementado)*
- `/orden/pagar` [POST]: Pagar la orden. *(No implementado)*
- `/orden/factura` [POST]: Consultar factura. *(No implementado)*

### Clientes

Contiene las operaciones necesarias de los clientes

- `/clientes` [GET]: Consulta al información de los clientes basado en parámetros de búsqueda *(No implementado)*
- `/cliente` [POST]: Crea el cliente de acuerdo con los parámetros recibidos. *(No implementado)*
- `/cliente` [PUT]: Actualizar el cliente de acuerdo con los parámetros recibidos. *(No implementado)*

### Productos

Contiene las operaciones sobre los productos.

- `/productos` [GET]: Consulta al información de los productos basado en parámetros de búsqueda *(No implementado)*
- `/producto` [POST]: Crea el producto de acuerdo con los parámetros recibidos. *(No implementado)*
- `/producto` [PUT]: Actualizar el producto de acuerdo con los parámetros recibidos. *(No implementado)*
- `/producto` [DELETE]: Eliminar el producto. *(No implementado)*
- `/producto/campanias` [GET]: Consulta al información de las campañas basado en parámetros de busqueda *(No implementado)*
- `/producto/campania` [POST]: Crea la campaña de acuerdo con los parámetros recibidos. *(No implementado)*
- `/producto/campania` [PUT]: Actualizar la campaña de acuerdo con los parámetros recibidos. *(No implementado)*
- `/producto/campania` [DELETE]: Eliminar la campaña. *(No implementado)*

### Convenios

Contiene las operaciones sobre los proveedores/convenios/alianzas.

- `/convenios` [GET]: Consulta al información de los convenios basado en parámetros de búsqueda *(No implementado)*
- `/convenio` [POST]: Crea el convenio de acuerdo con los parámetros recibidos. *(No implementado)*
- `/convenio` [PUT]: Actualizar el convenio de acuerdo con los parámetros recibidos. *(No implementado)*
- `/convenio` [DELETE]: Eliminar el convenio. *(No implementado)*

### Facturación

Contiene las operaciones en la facturación.

- `/facturacion/conciliacion` [POST]: Realiza la conciliación con el sistema contable de Toures Balon *(No implementado)*

## Tecnologías

Hecha con Spring Boot
API Gateway: 
Contenerización: 
Patrones usados: Composite (Orquestación), API Gateway, Microservicios.

## API REST

En este link se encuentra toda la información del API expuesto para el consumo de las aplicaciones del cliente: 

[https://app.swaggerhub.com/apis/ujaveriana/toures-balon/1.0.0#trial](https://app.swaggerhub.com/apis/ujaveriana/toures-balon/1.0.0#trial)

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

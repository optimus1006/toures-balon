Spring-Boot Camel

#Instalar

mvn clean install

#Crear secrets

oc create -f secret.yml

#Crear Propiedades***********Por favor verfifique los valores correspodientes a su ambiente

oc create configmap gatewayenrutador --from-literal=quickstart.endpoint_Enrutador_Path="/enrutador" --from-literal=quickstart.urlBroker="tcp://localhost:61616" --from-literal=quickstart.TimeOutBroker="3000" --from-literal=quickstart.urlEndpoint_service="localhost:8080/touresbalon/enrutador/gatewayEnrutador" --from-literal=quickstart.query_user_key="" --from-literal=quickstart.query_value="" --from-literal=quickstart.urlElasticSearch="localhost:8088/log/OrquestadorBroker/enviarLog" --from-literal=quickstart.wsdlConsume_avianca="ec2-18-191-167-228.us-east-2.compute.amazonaws.com:9090/servicesREST/AVIANCA/consultarVuelos" --from-literal=quickstart.codigoRespuesta_ErrorGeneral="500" --from-literal=quickstart.codigo_ErrorEstructura="500" --from-literal=quickstart.descripcion_ErrorEstructura="Error en estructura de la peticion"	 --from-literal=quickstart.codigo_ErrorInterno="500" --from-literal=quickstart.descripcion_ErrorInterno="Error interno del servicio" --from-literal=quickstart.codigo_ErrorProveedor="500" --from-literal=quickstart.descripcion_ErrorProveedor="Error de conexion con el proveedor" --from-literal=quickstart.codigo_ErrorTimeOut="500" --from-literal=quickstart.descripcion_ErrorTimeOut="Error de Time Out" --from-literal=quickstart.codigo_ErrorProcedure="500" --from-literal=quickstart.descripcion_ErrorProcedure="Error al ejecutar procedimiento almacenado"


#Desplegar en OpenShift
mvn clean -DskipTests fabric8:deploy -Popenshift

#To list all the running pods:

oc get pods
Log oc logs
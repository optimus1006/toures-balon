package com.touresbalon.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.touresbalon.api.service.ClienteService;
import com.touresbalon.service.domain.Cliente;
import com.touresbalon.service.domain.ClientesGETAllRS;
import com.touresbalon.service.domain.ClientesGETByIdRs;
import com.touresbalon.service.domain.ClientesPCTRq;
import com.touresbalon.service.domain.ClientesPCTRs;
import com.touresbalon.service.domain.ClientesPSTRq;
import com.touresbalon.service.domain.ClientesPSTRs;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-04-09T13:17:10.375-05:00[America/Bogota]")

@RestController
@RequestMapping("${openapi.clientes.base-path:/archetype/Clientes/1.0.0}")
public class ClientesApiController implements ClientesApi {

	private final NativeWebRequest request;

	@Autowired
	private ClienteService clienteService;

	@org.springframework.beans.factory.annotation.Autowired
	public ClientesApiController(NativeWebRequest request) {
		this.request = request;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@ApiOperation(value = "Registrar un nuevo cliente", nickname = "clientesPST", notes = "Crea el cliente de acuerdo con los parámetros recibidos.", response = ClientesPSTRs.class, tags = {
			"Clientes", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente registrado exitosamente", response = ClientesPSTRs.class),
			@ApiResponse(code = 400, message = "Parametro Invalido"),
			@ApiResponse(code = 409, message = "Cliente ya estaba registrado"),
			@ApiResponse(code = 500, message = "Error del sistema") })
	@RequestMapping(value = "/clientes", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Object> clientesPST(
			@ApiParam(value = "Cliente a registrar") @Valid @RequestBody ClientesPSTRq clientesPSTRq) {
		ClientesPSTRs clientesPSTRs = new ClientesPSTRs();
		Cliente cliente = new Cliente();
		cliente.setId(clienteService.createCliente(clientesPSTRq.getCliente()));
		clientesPSTRs.setCliente(cliente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Consulta al información de los clientes basado en parámetros de búsqueda", nickname = "clientesGETAll", notes = "Consulta al información de los clientes basado en parámetros de búsqueda ", response = ClientesGETAllRS.class, tags = {
			"Clientes", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Consulta exitosa", response = ClientesGETAllRS.class),
			@ApiResponse(code = 400, message = "Parametro invalido"),
			@ApiResponse(code = 500, message = "Error del sistema"),
			@ApiResponse(code = 401, message = "No autorizado") })
	@RequestMapping(value = "/clientes", produces = { "application/json" }, method = RequestMethod.GET)
	public ClientesGETAllRS clientesGETAll(
			@ApiParam(value = "El número de clientes a omitir antes de comenzar a recopilar el conjunto de resultados") @Valid @RequestParam(value = "offset", required = false) Integer offset,
			@ApiParam(value = "El número de clientes a retornar") @Valid @RequestParam(value = "limit", required = false) Integer limit,
			@ApiParam(value = "Nombres para consultar") @Valid @RequestParam(value = "nombres", required = false) Integer nombres,
			@ApiParam(value = "Apellidos para consultar") @Valid @RequestParam(value = "apellidos", required = false) Integer apellidos,
			@ApiParam(value = "Estado del cliente.", allowableValues = "ACTIVO, INACTIVO") @Valid @RequestParam(value = "estado", required = false) String estado) {
		ClientesGETAllRS clientesGETAllRS = new ClientesGETAllRS();
		clientesGETAllRS.setClientes(clienteService.getAllClientes());
		return clientesGETAllRS;
	}

	@ApiOperation(value = "Consulta al información de un cliente por su id", nickname = "clientesGETById", notes = "Consulta al información de un cliente por su id ", response = ClientesGETByIdRs.class, tags = {
			"Clientes", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Consulta exitosa", response = ClientesGETByIdRs.class),
			@ApiResponse(code = 400, message = "Parametro invalido"),
			@ApiResponse(code = 500, message = "Error del sistema"),
			@ApiResponse(code = 401, message = "No autorizado") })
	@RequestMapping(value = "/clientes/{identificacion}", produces = { "application/json" }, method = RequestMethod.GET)
	public ClientesGETByIdRs clientesGETById(
			@ApiParam(value = "Id de cliente", required = true) @PathVariable("identificacion") String identificacion) {
		ClientesGETByIdRs clientesGETByIdRs = new ClientesGETByIdRs();
		Cliente cliente = clienteService.getByIdentificacion(identificacion);
		clientesGETByIdRs.setCliente(cliente);
		return clientesGETByIdRs;

	}
	
	@ApiOperation(value = "Actualizar información del cliente", nickname = "clientesPCT", notes = "Actualiza el cliente de acuerdo con los parámetros recibidos.", response = ClientesPCTRs.class, tags={ "Clientes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Cliente actualizado exitosamente", response = ClientesPCTRs.class),
        @ApiResponse(code = 400, message = "Parametro Invalido"),
        @ApiResponse(code = 500, message = "Error del sistema") })
    @RequestMapping(value = "/clientes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    public ResponseEntity<ClientesPCTRs> clientesPCT(@ApiParam(value = "Cliente a actualizar"  )  @Valid @RequestBody ClientesPCTRq clientesPCTRq) {
		clienteService.updateCliente(clientesPCTRq.getCliente());
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
	
	
	@ApiOperation(value = "Borrar el cliente por su id", nickname = "clientesDEL", notes = "Borra el cliente de acuerdo al id recibido.", tags={ "Clientes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Cliente borrado exitosamente"),
        @ApiResponse(code = 400, message = "Parametro Invalido"),
        @ApiResponse(code = 500, message = "Error del sistema") })
    @RequestMapping(value = "/clientes/{idCliente}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> clientesDEL(@ApiParam(value = "Id de cliente",required=true) @PathVariable("idCliente") Long idCliente) {
        clienteService.deleteCliente(idCliente);
		return new ResponseEntity<>(HttpStatus.CREATED);

    }
}

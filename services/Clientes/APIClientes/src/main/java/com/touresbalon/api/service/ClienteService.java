package com.touresbalon.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.ClienteEntity;
import com.touresbalon.api.repository.ClienteRepository;
import com.touresbalon.service.domain.Categoria;
import com.touresbalon.service.domain.Cliente;
import com.touresbalon.service.domain.ClienteException;
import com.touresbalon.service.domain.Direccion;
import com.touresbalon.service.domain.Estado;
import com.touresbalon.service.domain.Tarjeta;
import com.touresbalon.service.domain.TipoIdentificacion;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
    private TarjetaService tarjetaService;
	
	@Autowired
    private DireccionService direccionService;
		
	public Long createCliente(Cliente cliente) throws ClienteException {
		ClienteEntity clienteEntity = new ClienteEntity(
				cliente.getIdentificacion(),
				cliente.getNombres(),
				cliente.getApellidos(),
				cliente.getTelefono(),
				cliente.getEmail(),
				cliente.getCelular(),
				cliente.getEstado().getCodigo(),
				cliente.getCategoria().getCodigo(),
				cliente.getTipoIdentificacion().getCodigo()
        );
		//clienteEntity.setId(0L);
		clienteRepository.save(clienteEntity);
		Long idCliente= clienteEntity.getId();
		
		for(Tarjeta tarjeta: cliente.getTarjetas()) {
			tarjetaService.createTarjeta(tarjeta, idCliente);
		}
		
		for(Direccion direccion: cliente.getDirecciones()) {
			direccionService.crearDireccion(direccion, idCliente);
		}
		
		return idCliente;
    }
	
	public List<Cliente> getAllClientes() throws ClienteException {
		List<ClienteEntity> clientes = clienteRepository.findAll();
		List<Cliente> clientesResponse = new ArrayList<>();
		
		
		for(ClienteEntity cliente: clientes) {
			Cliente clienteResponse = new Cliente();
			clienteResponse.setId(cliente.getId());
			clienteResponse.setNombres(cliente.getNombres());
			clienteResponse.setApellidos(cliente.getApellidos());
			clienteResponse.setEmail(cliente.getEmail());
			clienteResponse.setTelefono(cliente.getTelefono());
			clienteResponse.setCelular(cliente.getCelular());
			clienteResponse.setIdentificacion(cliente.getIdentificacion());
			Estado estado = new Estado();
			estado.setCodigo(cliente.getEstado());
			clienteResponse.setEstado(estado);
			Categoria categoria = new Categoria();
			categoria.setCodigo(cliente.getCategoria());
			clienteResponse.setCategoria(categoria);
			TipoIdentificacion tipoIdentificacion =  new TipoIdentificacion();
			tipoIdentificacion.setCodigo(cliente.getTipoIdentificacion());
			clienteResponse.setTipoIdentificacion(tipoIdentificacion);
			List<Direccion> direcciones = direccionService.getAllDireccionesByClienteId(cliente.getId());
			List<Tarjeta> tarjetas = tarjetaService.getAllByClienteId(cliente.getId());
			clienteResponse.setDirecciones(direcciones);
			clienteResponse.setTarjetas(tarjetas);
			clientesResponse.add(clienteResponse);
		}
		return clientesResponse;
	}
	
	public Cliente getByIdentificacion(String identificacion) throws ClienteException{
		ClienteEntity cliente= clienteRepository.findByIdentificacion(identificacion);
		Cliente clienteResponse = new Cliente();
		clienteResponse.setId(cliente.getId());
		clienteResponse.setNombres(cliente.getNombres());
		clienteResponse.setApellidos(cliente.getApellidos());
		clienteResponse.setEmail(cliente.getEmail());
		clienteResponse.setTelefono(cliente.getTelefono());
		clienteResponse.setCelular(cliente.getCelular());
		clienteResponse.setIdentificacion(cliente.getIdentificacion());
		Estado estado = new Estado();
		estado.setCodigo(cliente.getEstado());
		clienteResponse.setEstado(estado);
		Categoria categoria = new Categoria();
		categoria.setCodigo(cliente.getCategoria());
		clienteResponse.setCategoria(categoria);
		TipoIdentificacion tipoIdentificacion =  new TipoIdentificacion();
		tipoIdentificacion.setCodigo(cliente.getTipoIdentificacion());
		clienteResponse.setTipoIdentificacion(tipoIdentificacion);
		List<Direccion> direcciones = direccionService.getAllDireccionesByClienteId(cliente.getId());
		List<Tarjeta> tarjetas = tarjetaService.getAllByClienteId(cliente.getId());
		clienteResponse.setDirecciones(direcciones);
		clienteResponse.setTarjetas(tarjetas);
		return clienteResponse;
	}
	
	public void updateCliente(Cliente cliente) throws ClienteException {
		ClienteEntity clienteEntity = clienteRepository.findById(cliente.getId());
		
		clienteEntity.setId(cliente.getId());
		
		if(cliente.getNombres()!=null) {
			clienteEntity.setNombres(cliente.getNombres());
		}
		
		if(cliente.getApellidos()!=null) {
			clienteEntity.setApellidos(cliente.getApellidos());
		}
		
		if(cliente.getEmail()!=null) {
			clienteEntity.setEmail(cliente.getEmail());
		}
		
		if(cliente.getTelefono()!=null) {
			clienteEntity.setTelefono(cliente.getTelefono());
		}
		
		if(cliente.getCelular()!=null) {
			clienteEntity.setCelular(cliente.getCelular());
		}
		
		if(cliente.getEstado()!=null) {
			clienteEntity.setEstado(cliente.getEstado().getCodigo());
		}
		
		if(cliente.getCategoria()!=null) {
			clienteEntity.setCategoria(cliente.getCategoria().getCodigo());
		}
		
		if(cliente.getTipoIdentificacion()!=null) {
			clienteEntity.setTipoIdentificacion(cliente.getTipoIdentificacion().getCodigo());
		}
		
		clienteRepository.save(clienteEntity);
		
		if(cliente.getTarjetas()!=null) {
			tarjetaService.updateTarjetas(cliente.getTarjetas());
		}
		
		if(cliente.getDirecciones()!=null) {
			direccionService.updateDirecciones(cliente.getDirecciones());
		}
	}
	
	public void deleteCliente(Long idCliente){
		tarjetaService.deleteTarjetasByIdCliente(idCliente);
		direccionService.deleteDireccionesByIdCliente(idCliente);
		clienteRepository.deleteById(idCliente);
	}

}

package com.touresbalon.api.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.touresbalon.api.repository.read.ReadCategoriaEntity;
import com.touresbalon.api.repository.read.ReadCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.read.ReadClienteEntity;
import com.touresbalon.api.repository.read.ReadClienteRepository;
import com.touresbalon.api.repository.write.WriteClienteEntity;
import com.touresbalon.api.repository.write.WriteClienteRepository;
import com.touresbalon.service.domain.Categoria;
import com.touresbalon.service.domain.Cliente;
import com.touresbalon.service.domain.ClienteException;
import com.touresbalon.service.domain.Direccion;
import com.touresbalon.service.domain.Estado;
import com.touresbalon.service.domain.Tarjeta;
import com.touresbalon.service.domain.TipoIdentificacion;
import com.touresbalon.service.domain.Tarjeta.EstadoEnum;

@Service
@Transactional
public class ClienteServices {
	
	@Autowired
    private TarjetaService tarjetaService;
	
	@Autowired
    private DireccionService direccionService;

	@Autowired
	private ReadCategoriaRepository readCategoriaRepository;
	
	private final ReadClienteRepository readRepo;
	private final WriteClienteRepository writeRepo;
	  
	  @Autowired
	  ClienteServices(WriteClienteRepository writeRepo, ReadClienteRepository readRepo) {
	    this.writeRepo = writeRepo;
	    this.readRepo = readRepo;
	  }
		
	public Long createCliente(Cliente cliente) throws ClienteException {
		try {
			WriteClienteEntity clienteEntity = new WriteClienteEntity(
					cliente.getIdentificacion(),
					cliente.getNombres(),
					cliente.getApellidos(),
					cliente.getTelefono(),
					cliente.getEmail(),
					cliente.getCelular(),
					cliente.getEstado().getCodigo(),
					cliente.getCategoria().getCodigo(),
					cliente.getTipoIdentificacion().getCodigo(),
					new Timestamp(System.currentTimeMillis())
	        );
			writeRepo.save(clienteEntity);
			Long idClienteW= clienteEntity.getId();
			
			ReadClienteEntity clienteReadEntity = new ReadClienteEntity(
					cliente.getIdentificacion(),
					cliente.getNombres(),
					cliente.getApellidos(),
					cliente.getTelefono(),
					cliente.getEmail(),
					cliente.getCelular(),
					cliente.getEstado().getCodigo(),
					cliente.getCategoria().getCodigo(),
					cliente.getTipoIdentificacion().getCodigo(),
					new Timestamp(System.currentTimeMillis())
	        );
			readRepo.save(clienteReadEntity);
			Long idCliente= clienteReadEntity.getId();
			
			for(Tarjeta tarjeta: cliente.getTarjetas()) {
				tarjetaService.createTarjetaWrite(tarjeta, idClienteW);
				tarjetaService.createTarjetaRead(tarjeta, idCliente);
			}
			
			for(Direccion direccion: cliente.getDirecciones()) {
				direccionService.crearDireccionWrite(direccion, idClienteW);
				direccionService.crearDireccionRead(direccion, idCliente);
			}
			
			return idCliente;
		}
		catch(ClienteException ex) {
			throw new ClienteException("Se presento un error en la solicitud",ex);
		}
    }
	
	public List<Cliente> getAllClientes() throws ClienteException {
		List<ReadClienteEntity> clientes = readRepo.findAll();
		List<Cliente> clientesResponse = new ArrayList<>();
		
		for(ReadClienteEntity cliente: clientes) {
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
	
	public Cliente getById(Long id) throws ClienteException{
		ReadClienteEntity cliente= readRepo.findById(id);
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
		ReadCategoriaEntity categoriaEntity = readCategoriaRepository.getOne(cliente.getCategoria());
		Categoria categoria = new Categoria();
		categoria.setCodigo(categoriaEntity.getCodigo());
		categoria.setNombre(categoriaEntity.getNombre());
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
	
	public void updateCliente(Cliente cliente,Long id) throws ClienteException {
		WriteClienteEntity clienteWriteEntity = new WriteClienteEntity();
		ReadClienteEntity clienteReadEntity = readRepo.findById(id);
		
		clienteWriteEntity.setIdentificacion(clienteReadEntity.getIdentificacion());
		
		
		if(cliente.getNombres()!=null) {
			clienteWriteEntity.setNombres(cliente.getNombres());
			clienteReadEntity.setNombres(cliente.getNombres());
		}
		
		if(cliente.getApellidos()!=null) {
			clienteWriteEntity.setApellidos(cliente.getApellidos());
			clienteReadEntity.setApellidos(cliente.getApellidos());
		}
		
		if(cliente.getEmail()!=null) {
			clienteWriteEntity.setEmail(cliente.getEmail());
			clienteReadEntity.setEmail(cliente.getEmail());
		}
		
		if(cliente.getTelefono()!=null) {
			clienteWriteEntity.setTelefono(cliente.getTelefono());
			clienteReadEntity.setTelefono(cliente.getTelefono());
		}
		
		if(cliente.getCelular()!=null) {
			clienteWriteEntity.setCelular(cliente.getCelular());
			clienteReadEntity.setCelular(cliente.getCelular());
		}
		
		if(cliente.getEstado()!=null) {
			clienteWriteEntity.setEstado(cliente.getEstado().getCodigo());
			clienteReadEntity.setEstado(cliente.getEstado().getCodigo());
		}
		
		if(cliente.getCategoria()!=null) {
			clienteWriteEntity.setCategoria(cliente.getCategoria().getCodigo());
			clienteReadEntity.setCategoria(cliente.getCategoria().getCodigo());
		}
		
		if(cliente.getTipoIdentificacion()!=null) {
			clienteWriteEntity.setTipoIdentificacion(cliente.getTipoIdentificacion().getCodigo());
			clienteReadEntity.setTipoIdentificacion(cliente.getTipoIdentificacion().getCodigo());
		}
		
		writeRepo.save(clienteWriteEntity);
		readRepo.save(clienteReadEntity);
		
		
		
		if(cliente.getTarjetas()!=null) {
			tarjetaService.updateTarjetas(cliente.getTarjetas(),cliente.getId());
		}
		
		if(cliente.getDirecciones()!=null) {
			direccionService.updateDirecciones(cliente.getDirecciones(),cliente.getId());
		}
	}
	
	
	public void deleteCliente(Long idCliente){
		WriteClienteEntity clienteEntity = new WriteClienteEntity();
		ReadClienteEntity clienteReadEntity = readRepo.findById(idCliente);
		clienteEntity.setIdentificacion(clienteReadEntity.getIdentificacion());
		clienteEntity.setEstado(2);
		writeRepo.save(clienteEntity);
		clienteReadEntity.setEstado(2);
		readRepo.save(clienteReadEntity);
	}

}

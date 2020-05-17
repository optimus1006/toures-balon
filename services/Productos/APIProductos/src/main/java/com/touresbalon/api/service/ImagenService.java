package com.touresbalon.api.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.domain.Imagen;
import com.touresbalon.api.repository.ImagenEntity;
import com.touresbalon.api.repository.ImagenRepository;

@ApplicationScoped
@Transactional
public class ImagenService {

	@Inject
	ImagenRepository imagenRepository;
	
	public Imagen crearImagen(Imagen imagen, Long idHospedaje) throws HospedajeException{
		ImagenEntity imagenEntity = new ImagenEntity();
		
		if(imagen.getNombre()!=null) {
			imagenEntity.setNombre(imagen.getNombre());
		}
		else {
			throw new HospedajeException("El nombre de la imagen es obligatorio");
		}
		
		if(imagen.getDimensiones().getAlto()!=null) {
			imagenEntity.setAlto(imagen.getDimensiones().getAlto());
		}
		
		if(imagen.getDimensiones().getAncho()!=null) {
			imagenEntity.setAncho(imagen.getDimensiones().getAncho());
		}
		
		if(imagen.getPath()!=null) {
			imagenEntity.setPath(imagen.getPath());
		}
		else {
			throw new HospedajeException("la ruta de la imagen es obligatorio");
		}
		
		imagenEntity.setId_hospedaje(idHospedaje);
		
		Imagen imagenResponse = new Imagen();
		
		imagenRepository.save(imagenEntity);
		
		imagenResponse.setId(imagenEntity.getId());
		
		return imagenResponse;
	}
}

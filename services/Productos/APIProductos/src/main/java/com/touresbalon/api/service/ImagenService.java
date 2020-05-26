package com.touresbalon.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.domain.Imagen;
import com.touresbalon.api.domain.ImagenDimensiones;
import com.touresbalon.api.repository.ImagenEntity;
import com.touresbalon.api.repository.ImagenRepository;

@ApplicationScoped
@Transactional
public class ImagenService {

	@Inject
	ImagenRepository imagenRepository;

	public Imagen crearImagen(Imagen imagen, Long idObjeto, String tipo) throws HospedajeException {
		ImagenEntity imagenEntity = new ImagenEntity();

		if (imagen.getNombre() != null) {
			imagenEntity.setNombre(imagen.getNombre());
		} else {
			throw new HospedajeException("El nombre de la imagen es obligatorio");
		}

		if (imagen.getDimensiones().getAlto() != null) {
			imagenEntity.setAlto(imagen.getDimensiones().getAlto());
		}

		if (imagen.getDimensiones().getAncho() != null) {
			imagenEntity.setAncho(imagen.getDimensiones().getAncho());
		}

		if (imagen.getPath() != null) {
			imagenEntity.setPath(imagen.getPath());
		} else {
			throw new HospedajeException("la ruta de la imagen es obligatorio");
		}

		if (tipo != null) {
			imagenEntity.setTipo_imagen(tipo);
			if (tipo.equals("HOSPEDAJE")) {
				imagenEntity.setId_hospedaje(idObjeto);
			} else {
				imagenEntity.setId_evento(idObjeto);
			}
		} else {
			throw new HospedajeException("el tipo de imagen es obligatorio");
		}

		Imagen imagenResponse = new Imagen();

		imagenRepository.save(imagenEntity);

		imagenResponse.setId(imagenEntity.getId());

		return imagenResponse;
	}

	public Imagen actualizar(Imagen imagen) throws HospedajeException {
		
		Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagen.getId());

		if(imagenEntity.isPresent()) {
			if (imagen.getNombre() != null) {
				imagenEntity.get().setNombre(imagen.getNombre());
			}

			if (imagen.getDimensiones().getAlto() != null) {
				imagenEntity.get().setAlto(imagen.getDimensiones().getAlto());
			}

			if (imagen.getDimensiones().getAncho() != null) {
				imagenEntity.get().setAncho(imagen.getDimensiones().getAncho());
			}

			if (imagen.getPath() != null) {
				imagenEntity.get().setPath(imagen.getPath());
			}

			Imagen imagenResponse = new Imagen();

			imagenRepository.save(imagenEntity.get());

			imagenResponse.setId(imagen.getId());

			return imagenResponse;
		}
		else {
			throw new HospedajeException("la imagen con el id " + imagen.getId() + " No existe");
		}
		
	}
	
	public List<Imagen> buscarImagenesPorTipoPorId(String tipo, Long id) throws HospedajeException {
		
		List<ImagenEntity> imagenesEntity = new ArrayList<>();
		List<Imagen> imagenes = new ArrayList<>(); 
		
		if(tipo.equals("HOSPEDAJE")) {
			imagenesEntity = imagenRepository.findByTipo_imagenAndId_hospedaje(tipo, id);
		}
		else {
			imagenesEntity = imagenRepository.findByTipo_imagenAndId_evento(tipo, id);
		}
		
		if(imagenesEntity.size()>0) {
			for(ImagenEntity imagenEntity : imagenesEntity) {
				Imagen imagen = new Imagen();
				imagen.setId(imagenEntity.getId());
				ImagenDimensiones imagenDimensiones = new ImagenDimensiones();
				imagenDimensiones.setAlto(imagenEntity.getAlto());
				imagenDimensiones.setAncho(imagenEntity.getAncho());
				imagen.setDimensiones(imagenDimensiones);
				imagen.setNombre(imagenEntity.getNombre());
				imagen.setPath(imagenEntity.getPath());
				imagenes.add(imagen);
			}
		}
		return imagenes;
	}
}

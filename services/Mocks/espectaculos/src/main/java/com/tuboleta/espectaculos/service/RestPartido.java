package com.tuboleta.espectaculos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tuboleta.espectaculos.vo.RequestRest;
import com.tuboleta.espectaculos.vo.Response;
import com.tuboleta.espectaculos.vo.VOPartido;

@RestController
@RequestMapping(path = "/servicesREST/TUBOLETA")
public class RestPartido {

	private List<VOPartido> partidos = createListPartidos();

	@RequestMapping(method = RequestMethod.POST, path = "/consultarEventos", consumes = "application/json", produces = "application/json")
	public @ResponseBody List<VOPartido> consultarEventos(@RequestBody RequestRest request) throws Exception {
		System.out.println("Clasificacion Evento: " + request.getClasificacionEvento());
		try {
			if (request.getClasificacionEvento().equals("Deportes")) {
				return partidos;
			}
			return null;
		} catch (Exception ex) {

			return null;
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/reservarEvento", consumes = "application/json", produces = "application/json")
	public @ResponseBody Response reservarEvento(@RequestBody RequestRest request) {
		System.out.println("Nombre del evento: " + request.getNombreEvento());
		System.out.println("Numero de Reserva: " + request.getNumReserva());
		System.out.println("Nombre Cliente: " + request.getNombre());
		System.out.println("Identificacion Cliente: " + request.getIdentificacion());

		Integer numReserva = getRandomNumberUsingInts(1,100000);
		String mensaje = "Reserva realizada Exitosamente";
		if (numReserva < 20000) {
			numReserva = 0;
			mensaje = "No se pudo realizar la reserva solicitada";
		}

		return new Response(numReserva, mensaje);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/pagarEvento", consumes = "application/json", produces = "application/json")
	public @ResponseBody Response pagarEvento(@RequestBody RequestRest request) {
		System.out.println("Numero de reserva: " + request.getNumReserva());
		System.out.println("Valor: " + request.getValor());
		return new Response(request.getNumReserva(), "Reserva Pagada Exitosamente por valor de: "+request.getValor());
	}

	public int getRandomNumberUsingInts(int min, int max) {
		Random random = new Random();
		return random.ints(min, max)
				.findFirst()
				.getAsInt();
	}

	private static List<VOPartido> createListPartidos() {
		List<VOPartido> tempPartidos = new ArrayList<>();

		VOPartido partido1 = new VOPartido();
		partido1.setNombreEvento("ELIMINATORIAS QATAR 2022");
		partido1.setClasificacion("Deportes");
		partido1.setFechaHora("jueves 3 septiembre 2020  15:30");
		partido1.setLugar("Estadio Metropolitano (Barranquilla)");
		partido1.setPrecio(new Random().nextDouble() * 100000);
		partido1.setUbicacion("OCCIDENTAL BAJA");

		VOPartido partido2 = new VOPartido();
		partido2.setNombreEvento("ELIMINATORIAS QATAR 2022");
		partido2.setClasificacion("Deportes");
		partido2.setFechaHora("jueves 3 septiembre 2020  15:30");
		partido2.setLugar("Estadio Metropolitano (Barranquilla)");
		partido2.setPrecio(new Random().nextDouble() * 100000);
		partido2.setUbicacion("OCCIDENTAL ALTA");

		VOPartido partido3 = new VOPartido();
		partido3.setNombreEvento("ELIMINATORIAS QATAR 2022");
		partido3.setClasificacion("Deportes");
		partido3.setFechaHora("jueves 3 septiembre 2020  15:30");
		partido3.setLugar("Estadio Metropolitano (Barranquilla)");
		partido3.setPrecio(new Random().nextDouble() * 100000);
		partido3.setUbicacion("ORIENTAL ALTA");

		VOPartido partido4 = new VOPartido();
		partido4.setNombreEvento("ELIMINATORIAS QATAR 2022");
		partido4.setClasificacion("Deportes");
		partido4.setFechaHora("jueves 3 septiembre 2020  15:30");
		partido4.setLugar("Estadio Metropolitano (Barranquilla)");
		partido4.setPrecio(new Random().nextDouble() * 100000);
		partido4.setUbicacion("ORIENTAL BAJA");

		tempPartidos.add(partido1);
		tempPartidos.add(partido2);
		tempPartidos.add(partido3);
		tempPartidos.add(partido4);
		return tempPartidos;
	}
}
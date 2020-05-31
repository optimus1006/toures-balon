package com.hilton.hoteles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hilton.hoteles.vo.RequestRest;
import com.hilton.hoteles.vo.Response;
import com.hilton.hoteles.vo.VOHabitacion;

@RestController
@RequestMapping(path = "/servicesREST/HILTON")
public class RestHabitacion {

	private List<VOHabitacion> habitacionesBaq = createListBaq();

	@RequestMapping(method = RequestMethod.POST, path = "/consultarHabitaciones", consumes = "application/json", produces = "application/json")
	public @ResponseBody List<VOHabitacion> consultarHabitaciones(@RequestBody RequestRest request) throws Exception {
		System.out.println("Destino: " + request.getDestino());
		System.out.println("Fecha Entrada: " + request.getFechaEntrada());
		System.out.println("Fecha Salida: " + request.getFechaSalida());
		try {
			if (request.getDestino().equals("Barranquilla")) {
				return habitacionesBaq;
			}
			return null;
		} catch (Exception ex) {

			return null;
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/reservarHabitacion", consumes = "application/json", produces = "application/json")
	public @ResponseBody Response reservarHabitacion(@RequestBody RequestRest request) {
		System.out.println("Numero de habitación: " + request.getNumHabitacion());
		System.out.println("Nombre Pasajero: " + request.getNombre());
		System.out.println("Identificacion Pasajero: " + request.getIdentificacion());

		Integer numReserva = getRandomNumberUsingInts(1,100000);
		String mensaje = "Reserva realizada Exitosamente";
		if (numReserva < 20000) {
			numReserva = 0;
			mensaje = "No se pudo realizar la reserva solicitada";
		}

		return new Response(numReserva, mensaje);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/pagarHabitacion", consumes = "application/json", produces = "application/json")
	public @ResponseBody Response pagarHabitacion(@RequestBody RequestRest request) {
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

	private static List<VOHabitacion> createListBaq() {
		List<VOHabitacion> tempHabitaciones = new ArrayList<>();

		VOHabitacion hab1 = new VOHabitacion();
		hab1.setDestino("Barranquilla");
		hab1.setNombreHotel("Hampton by Hilton Barranquilla");
		hab1.setPrecio(new Random().nextDouble() * 100000);
		hab1.setTipoHabitacion("Doble Estándar");

		VOHabitacion hab2 = new VOHabitacion();
		hab2.setDestino("Barranquilla");
		hab2.setNombreHotel("Hampton by Hilton Barranquilla");
		hab2.setPrecio(new Random().nextDouble() * 100000);
		hab2.setTipoHabitacion("Jr. Suite");

		VOHabitacion hab3 = new VOHabitacion();
		hab3.setDestino("Barranquilla");
		hab3.setNombreHotel("Hampton by Hilton Barranquilla");
		hab3.setPrecio(new Random().nextDouble() * 100000);
		hab3.setTipoHabitacion("Suite Jacuzzi");

		VOHabitacion hab4 = new VOHabitacion();
		hab4.setDestino("Barranquilla");
		hab4.setNombreHotel("Hilton Garden Inn Barranquilla");
		hab4.setPrecio(new Random().nextDouble() * 100000);
		hab4.setTipoHabitacion("Doble Estándar");

		tempHabitaciones.add(hab1);
		tempHabitaciones.add(hab2);
		tempHabitaciones.add(hab3);
		tempHabitaciones.add(hab4);
		return tempHabitaciones;
	}
}
package com.avianca.vuelos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avianca.vuelos.vo.Pago;
import com.avianca.vuelos.vo.RequestRest;
import com.avianca.vuelos.vo.Reserva;
import com.avianca.vuelos.vo.VOVuelo;

@RestController
@RequestMapping(path = "/servicesREST/AVIANCA")
public class RestVuelo {

	private List<VOVuelo> vuelosBogBaq = createListBogBaq();

	@RequestMapping(method = RequestMethod.POST, path = "/consultarVuelos", consumes = "application/json", produces = "application/json")
	public @ResponseBody List<VOVuelo> consultarVuelos(@RequestBody RequestRest request) throws Exception {
		System.out.println("Origen: " + request.getOrigen());
		System.out.println("Destino: " + request.getDestino());
		try {
			if (request.getOrigen().equals("Bogota") && request.getDestino().equals("Barranquilla")) {
				return vuelosBogBaq;
			}
			return null;
		} catch (Exception ex) {

			return null;
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/reservarVuelo", consumes = "application/json", produces = "application/json")
	public @ResponseBody Reserva reservarVuelo(@RequestBody RequestRest request) {
		System.out.println("Numero de Vuelo: " + request.getNumVuelo());
		System.out.println("Nombre Pasajero: " + request.getNombre());
		System.out.println("Identificacion Pasajero: " + request.getIdentificacion());

		Integer numReserva = getRandomNumberUsingInts(1,100000);
		String mensaje = "Vuelo Reservado Exitosamente";
		if (numReserva < 20000) {
			numReserva = 0;
			mensaje = "No se pudo realizar la reserva del Vuelo solicitado";
		}

		return new Reserva(numReserva, mensaje);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/pagarVuelo", consumes = "application/json", produces = "application/json")
	public @ResponseBody Pago pagarVuelo(@RequestBody RequestRest request) {
		System.out.println("Numero de vuelo: " + request.getNumVuelo());
		System.out.println("Valor: " + request.getValor());
		return new Pago(request.getNumVuelo(), "Vuelo Pagado Exitosamente por valor de: " + request.getValor());
	}

	public int getRandomNumberUsingInts(int min, int max) {
		Random random = new Random();
		return random.ints(min, max)
				.findFirst()
				.getAsInt();
	}

	private static List<VOVuelo> createListBogBaq() {
		List<VOVuelo> tempVuelos = new ArrayList<>();
		VOVuelo vuelo1 = new VOVuelo();
		vuelo1.setOrigen("Bogotá");
		vuelo1.setDestino("Barranquilla");
		vuelo1.setNumVuelo(new Random().nextInt() * 100000);
		try {
			vuelo1.setHoraSalida(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-02 06:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vuelo1.setPrecio(new Random().nextDouble() * 100000);
		vuelo1.setAsientosLibres(4);

		VOVuelo vuelo2 = new VOVuelo();
		vuelo2.setOrigen("Bogotá");
		vuelo2.setDestino("Barranquilla");
		vuelo2.setNumVuelo(new Random().nextInt() * 100000);
		try {
			vuelo2.setHoraSalida(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-02 09:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vuelo2.setPrecio(new Random().nextDouble() * 100000);
		vuelo2.setAsientosLibres(1);

		VOVuelo vuelo3 = new VOVuelo();
		vuelo3.setOrigen("Bogotá");
		vuelo3.setDestino("Barranquilla");
		vuelo3.setNumVuelo(new Random().nextInt() * 100000);
		try {
			vuelo3.setHoraSalida(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-02 12:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vuelo3.setPrecio(new Random().nextDouble() * 100000);
		vuelo3.setAsientosLibres(2);

		VOVuelo vuelo4 = new VOVuelo();
		vuelo4.setOrigen("Bogotá");
		vuelo4.setDestino("Barranquilla");
		vuelo4.setNumVuelo(new Random().nextInt() * 100000);
		try {
			vuelo4.setHoraSalida(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-02 15:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vuelo4.setPrecio(new Random().nextDouble() * 100000);
		vuelo4.setAsientosLibres(2);
		tempVuelos.add(vuelo1);
		tempVuelos.add(vuelo2);
		tempVuelos.add(vuelo3);
		tempVuelos.add(vuelo4);
		return tempVuelos;
	}
}
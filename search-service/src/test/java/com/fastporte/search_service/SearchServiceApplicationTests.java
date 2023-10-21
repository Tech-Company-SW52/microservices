package com.fastporte.search_service;

import com.fastporte.search_service.entity.Carrier;
import com.fastporte.search_service.entity.Vehicle;
import com.fastporte.search_service.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SearchServiceApplicationTests {

	@Autowired
	private SearchService searchService;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void testFilterTransportByName() {
		// Datos de prueba
		String nameToFilter = "Descripción 1";
		List<Vehicle> allVehicle = Arrays.asList(
		);
		// Transporte 1
		Vehicle transport1 = new Vehicle();
		transport1.setId(1L);
		transport1.setPhoto("transporte1.jpg");
		transport1.setType("Auto");
		transport1.setQuantity(4L);
		transport1.setCarrier(new Carrier(1L, "Juan", "Perez", "juanperez@example.com", "1234567890", "Peru", "1234567890", LocalDate.of(1990, 1, 1), "Descripcion de transporte 1"));
		allVehicle.add(transport1);

		// Transporte 2
		Vehicle transport2 = new Vehicle();
		transport2.setId(2L);
		transport2.setPhoto("transporte2.jpg");
		transport2.setType("Camion");
		transport2.setQuantity(3L);
		transport2.setCarrier(new Carrier(2L, "Maria", "Gonzalez", "mariagonzalez@example.com", "9876543210",  "Peru", "9876543210", LocalDate.of(1980, 2, 2), "Descripcion de transporte 2"));
		allVehicle.add(transport2);

		// Transporte 3
		Vehicle transport3 = new Vehicle();
		transport3.setId(3L);
		transport3.setPhoto("transporte3.jpg");
		transport3.setType("Auto");
		transport3.setQuantity(1L);
		transport3.setCarrier(new Carrier(3L, "Pedro", "Lopez", "pedrolopez@example.com", "6543210987","Peru", "6543210987", LocalDate.of(1970, 3, 3), "Descripcion de transporte 3"));
		allVehicle.add(transport3);



		// Llama al método que deseas probar
		List<Vehicle> filteredVehicle = searchService.filterTransportByVehicleType(allVehicle, nameToFilter);

		// Verifica el resultado esperado
		assertEquals(nameToFilter, filteredVehicle.get(0).getType()); // El resultado debe coincidir con el nombre filtrado
	}
}

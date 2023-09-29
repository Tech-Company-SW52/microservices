package com.fastporte.search_service;

import com.fastporte.search_service.entity.Transport;
import com.fastporte.search_service.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		String nameToFilter = "Transporte 1";
		List<Transport> allTransport = Arrays.asList(
				new Transport(1L, "Transporte 1", "Descripción 1"),
				new Transport(2L, "Transporte 2", "Descripción 2"),
				new Transport(3L, "Transporte 3", "Descripción 3")
		);

		// Llama al método que deseas probar
		List<Transport> filteredTransport = searchService.filerTransportByName(allTransport, nameToFilter);

		// Verifica el resultado esperado
		assertEquals(nameToFilter, filteredTransport.get(0).getName()); // El resultado debe coincidir con el nombre filtrado
	}
}

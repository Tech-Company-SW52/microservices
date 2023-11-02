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

}

package com.example.demo.batch.rest;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Employee;

/**
 * This class demonstrates how we can read the input of our batch job from an
 * external REST API.
 *
 */
class RESTEmployeeReader implements ItemReader<Employee> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RESTEmployeeReader.class);

	private final String apiUrl;
	private final RestTemplate restTemplate;

	private int nextEmployeeIndex;
	private List<Employee> employeeData;

	RESTEmployeeReader(String apiUrl, RestTemplate restTemplate) {
		this.apiUrl = apiUrl;
		this.restTemplate = restTemplate;
		nextEmployeeIndex = 0;
	}

	@Override
	public Employee read() throws Exception {
		LOGGER.info("Reading the information of the next employee");

		if (employeeDataIsNotInitialized()) {
			employeeData = fetchEmployeeDataFromAPI();
		}

		Employee nextEmployee = null;

		if (nextEmployeeIndex < employeeData.size()) {
			nextEmployee = employeeData.get(nextEmployeeIndex);
			nextEmployeeIndex++;
		}

		LOGGER.info("Found employee: {}", nextEmployee);

		return nextEmployee;
	}

	private boolean employeeDataIsNotInitialized() {
		return this.employeeData == null;
	}

	private List<Employee> fetchEmployeeDataFromAPI() {
		LOGGER.debug("Fetching employee data from an external API by using the url: {}", apiUrl);

		ResponseEntity<Employee[]> response = restTemplate.getForEntity(apiUrl, Employee[].class);
		Employee[] employeeData = response.getBody();
		LOGGER.debug("Found {} employees", employeeData.length);

		return Arrays.asList(employeeData);
	}
}

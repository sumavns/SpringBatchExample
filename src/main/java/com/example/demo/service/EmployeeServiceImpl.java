package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.constants.Constants;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	RestTemplate restTemplate;

	@Value("${emp.service.url}")
	private String serverUrl;

	@Autowired
	private EmployeeRepository employeeRepository;

	public void insertEmployeeDetails(int empid) throws ServiceException {
		Employee emp = new Employee();
		String uri = serverUrl + empid + "/deptDetails";
		logger.info("calling employee service");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(Constants.PARAMETERS, httpHeaders);
		Department dept = null;
		try {
			ResponseEntity<Department> response = restTemplate.exchange(uri, HttpMethod.POST, entity,
					new ParameterizedTypeReference<Department>() {
					});
			dept = response.getBody();
			// Loop through the API output

		} catch (ServiceException ex) {
			logger.error("Exception occurred in insertEmployeeDetails service method {}", ex);
		}

		// read JSON and load json
		ObjectMapper mapper = new ObjectMapper();
		String deptJsonString = null;
		try {
			deptJsonString = mapper.writeValueAsString(dept);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}

		TypeReference<Department> typeReference = new TypeReference<Department>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream(deptJsonString);
		try {
			Department department = mapper.readValue(inputStream, typeReference);
			// employeeRepository.save(department);
			System.out.println("Users Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save users: " + e.getMessage());
		}
	}

}

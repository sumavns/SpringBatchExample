package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.Constants;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@Validated
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService empServiceImpl;

	@PostMapping(value = "/employees/{empid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void insertEmployeeDetails(
			@PathVariable("empid") @NotNull(message = Constants.EMPID_NULL_MESSAGE) final int empid) {
		logger.info("inside insertEmployeeDetails method with param {}", empid);
		empServiceImpl.insertEmployeeDetails(empid);
	}

	private List<Employee> createEmployees() {
		Employee emp1 = new Employee();
		emp1.setEmpid(101);
		emp1.setEname("Tony");
		emp1.setSal(10000);

		Employee emp2 = new Employee();
		emp2.setEmpid(102);
		emp2.setEname("Suma");
		emp2.setSal(12000);

		Employee emp3 = new Employee();
		emp3.setEmpid(103);
		emp3.setEname("latha");
		emp3.setSal(15000);

		return Arrays.asList(emp1, emp2, emp3);
	}

}

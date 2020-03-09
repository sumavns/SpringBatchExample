package com.example.demo.reader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import com.example.demo.model.Employee;

public class EmployeeReader implements ItemReader<Employee> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeReader.class);

	private int nextEmployeeIndex;
	private List<Employee> empData;

	EmployeeReader() {
		initialize();
	}

	private void initialize() {
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

		empData = Collections.unmodifiableList(Arrays.asList(emp1, emp2, emp3));
		nextEmployeeIndex = 0;
	}

	@Override
	public Employee read() throws Exception {
		LOGGER.info("Reading the information of the next employee");

		Employee nextEmployee = null;

		if (nextEmployeeIndex < empData.size()) {
			nextEmployee = empData.get(nextEmployeeIndex);
			nextEmployeeIndex++;
		}

		LOGGER.info("Found employee: {}", nextEmployee);

		return nextEmployee;
	}
}

package com.example.demo.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeProcessor.class);

	@Override
    public Employee process(Employee item) throws Exception {
        LOGGER.info("Processing employee information: {}", item);
        return item;
    }
}
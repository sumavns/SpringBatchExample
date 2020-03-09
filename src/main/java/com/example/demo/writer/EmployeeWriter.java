package com.example.demo.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.example.demo.model.Employee;

public class EmployeeWriter implements ItemWriter<Employee> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWriter.class);

	@Override
	public void write(List<? extends Employee> items) throws Exception {
		LOGGER.info("Received the information of {} employees", items.size());

		items.forEach(i -> LOGGER.debug("Received the information of a employee: {}", i));
	}
}

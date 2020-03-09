package com.example.demo.reader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Employee;
import com.example.demo.processor.EmployeeProcessor;
import com.example.demo.writer.EmployeeWriter;

@Configuration
public class EmployeeJobConfig {

	@Bean
	ItemReader<Employee> inMemoryEmployeeReader() {
		return new EmployeeReader();
	}

	@Bean
	ItemProcessor<Employee, Employee> inMemoryEmployeeProcessor() {
		return new EmployeeProcessor();
	}

	@Bean
	ItemWriter<Employee> inMemoryEmployeeWriter() {
		return new EmployeeWriter();
	}

	@Bean
	Step inMemoryEmployeeStep(ItemReader<Employee> inMemoryEmployeeReader,
			ItemProcessor<Employee, Employee> inMemoryEmployeeProcessor, ItemWriter<Employee> inMemoryEmployeeWriter,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("inMemoryEmployeeStep").<Employee, Employee>chunk(1).reader(inMemoryEmployeeReader)
				.processor(inMemoryEmployeeProcessor).writer(inMemoryEmployeeWriter).build();
	}

	@Bean
	Job inMemoryEmployeeJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("inMemoryEmployeeStep") Step inMemoryEmployeeStep) {
		return jobBuilderFactory.get("inMemoryEmployeeJob").incrementer(new RunIdIncrementer()).flow(inMemoryEmployeeStep)
				.end().build();
	}

}

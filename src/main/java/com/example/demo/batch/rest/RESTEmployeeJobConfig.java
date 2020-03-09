package com.example.demo.batch.rest;

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
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Employee;
import com.example.demo.processor.EmployeeProcessor;
import com.example.demo.writer.EmployeeWriter;

@Configuration
public class RESTEmployeeJobConfig {

	private static final String PROPERTY_REST_API_URL = "rest.api.to.database.job.api.url";

	@Bean
	ItemReader<Employee> restEmployeeReader(Environment environment, RestTemplate restTemplate) {
		return new RESTEmployeeReader(environment.getRequiredProperty(PROPERTY_REST_API_URL), restTemplate);
	}

	@Bean
	ItemProcessor<Employee, Employee> restEmployeeProcessor() {
		return new EmployeeProcessor();
	}

	@Bean
	ItemWriter<Employee> restEmployeeWriter() {
		return new EmployeeWriter();
	}

	@Bean
	Step restEmployeeStep(ItemReader<Employee> restEmployeeReader,
			ItemProcessor<Employee, Employee> restEmployeeProcessor, ItemWriter<Employee> restEmployeeWriter,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("restEmployeeStep").<Employee, Employee>chunk(1).reader(restEmployeeReader)
				.processor(restEmployeeProcessor).writer(restEmployeeWriter).build();
	}

	@Bean
	Job restEmployeeJob(JobBuilderFactory jobBuilderFactory, @Qualifier("restEmployeeStep") Step restEmployeeStep) {
		return jobBuilderFactory.get("restEmployeeJob").incrementer(new RunIdIncrementer()).flow(restEmployeeStep).end()
				.build();
	}
}

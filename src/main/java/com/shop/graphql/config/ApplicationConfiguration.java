package com.shop.graphql.config;

import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import graphql.execution.ExecutionStrategy;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {
	private AsyncTransactionalExecutionStrategy asyncTransactionalExecutionStrategy;

	@Bean
	public GraphQLScalarType dateTime() {
		return ExtendedScalars.DateTime;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@Bean
	public SchemaParserOptions schemaParserOptions() {
		return SchemaParserOptions
			.newOptions()
			.objectMapperProvider(fieldDefinition -> objectMapper())
			.build();
	}

	@Bean
	public Map<String, ExecutionStrategy> executionStrategies() {
		Map<String, ExecutionStrategy> executionStrategyMap = new HashMap<>();
		executionStrategyMap.put("queryExecutionStrategy", asyncTransactionalExecutionStrategy);
		return executionStrategyMap;
	}
}

package com.shop.graphql.config;

import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {

			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors
					.stream()
					.filter(this::isClientError)
					.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors
					.stream()
					.filter(e -> !isClientError(e))
					.collect(Collectors.toList());

				List<GraphQLError> error = new ArrayList<>();
				error.addAll(clientErrors);
				error.addAll(serverErrors);

				return error;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(
					error instanceof ExceptionWhileDataFetching ||
					error instanceof Throwable
				);
			}
		};
	}

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
}

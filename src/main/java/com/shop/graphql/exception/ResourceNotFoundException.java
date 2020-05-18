package com.shop.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ResourceNotFoundException
	extends RuntimeException
	implements GraphQLError {
	private Map<String, Object> extensions = new HashMap<>();

	public ResourceNotFoundException(
		String resourse,
		String paramName,
		String param
	) {
		super(
			StringUtils.capitalize(resourse) +
			" with " +
			StringUtils.capitalize(paramName) +
			": " +
			param +
			" not found."
		);
		extensions.put(
			"invalid" +
			StringUtils.capitalize(resourse) +
			"_" +
			StringUtils.capitalize(paramName),
			param
		);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.DataFetchingException;
	}

	@Override
	public Map<String, Object> getExtensions() {
		return extensions;
	}
}

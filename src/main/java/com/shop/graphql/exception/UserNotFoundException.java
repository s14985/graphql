package com.shop.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNotFoundException
        extends RuntimeException
        implements GraphQLError {
    private Map<String, Object> extensions = new HashMap<>();

    public UserNotFoundException(String paramName, String param) {
        super("User with " + paramName + ": " + param + " not found.");
        extensions.put("invalidUser_" + paramName, param);
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

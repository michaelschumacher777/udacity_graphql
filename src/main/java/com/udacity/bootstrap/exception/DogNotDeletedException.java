package com.udacity.bootstrap.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogNotDeletedException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public DogNotDeletedException(String message, Long invalidDogId) {
        super(message);
        extensions.put("failedToDeleteDogId", invalidDogId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}

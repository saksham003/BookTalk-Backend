package com.saksham.BookTalk.exceptions;

public class ResourceAlreadyExistException extends RuntimeException{
    String resourceName;
    String resourceValue;
    String resource;

    public ResourceAlreadyExistException(String resourceName, String resourceValue, String resource) {
        super(String.format("%s already exists with %s: %s", resourceName, resourceValue, resource));
        this.resourceName = resourceName;
        this.resourceValue = resourceValue;
        this.resource = resource;
    }
}

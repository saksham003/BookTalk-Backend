package com.saksham.BookTalk.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String resource;

    public ResourceNotFoundException(String resourceName, String resource) {
        super(String.format("%s not found with value: %s", resourceName, resource));
        this.resourceName = resourceName;
        this.resource = resource;
    }
}

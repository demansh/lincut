package com.dem.lincut.persistence.sqlpersistence.exceptions;

public class CreateRecordException extends RepositoryException {
    public CreateRecordException(String url) {
        super(String.format("Failed to save url: %s", url));
    }
}


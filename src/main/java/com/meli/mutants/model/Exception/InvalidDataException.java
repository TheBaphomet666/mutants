package com.meli.mutants.model.Exception;

/**
 * Business exception for a data that is invalid
 */
public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message) {
        super(message);
    }

}

package com.example.easpringapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime exception that indicates that no franchise
 * with supplied id exist in system at the time of the request.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException {
    public FranchiseNotFoundException(int id) {
        super("Franchise does not exist with ID: " + id);
    }
}

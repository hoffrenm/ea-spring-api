package com.example.easpringapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime exception that indicates that no movie
 * with supplied id exist in system at the time of the request.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(int id) {
        super("Movie does not exist with ID: " + id);
    }
}


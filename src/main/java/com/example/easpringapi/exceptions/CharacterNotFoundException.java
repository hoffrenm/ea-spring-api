package com.example.easpringapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime exception that indicates that no character
 * with supplied id exist in system at the time of the request.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(int id) {
        super("Character does not exist with ID: " + id);
    }
}

package com.example.easpringapi.dto;


import lombok.Data;

import java.util.Set;

/**
 * Data transfer object that represents Movie model
 * for outgoing data transfer purposes.
 */
@Data
public class MovieDTO {
    private int id;
    private String title;
    private String genre;
    private String releaseYear;
    private String director;
    private Integer franchise;
    private Set<Integer> characters;
}
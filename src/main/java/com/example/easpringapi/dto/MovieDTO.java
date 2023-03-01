package com.example.easpringapi.dto;


import lombok.Data;

import java.util.Set;

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
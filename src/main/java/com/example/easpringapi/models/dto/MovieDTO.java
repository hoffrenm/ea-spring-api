package com.example.easpringapi.models.dto;

import com.example.easpringapi.models.Franchise;
import lombok.Data;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private String genre;
    private String releaseYear;
    private String director;
    private Franchise franchise;
}
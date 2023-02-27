package com.example.easpringapi.controllers;

import com.example.easpringapi.repositories.MovieRepository;
import com.example.easpringapi.services.MovieService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private MovieService movieservice;

    public MovieController(MovieService movieservice) {
        this.movieservice = movieservice;
    }

    //CRUD functionality to be added
}

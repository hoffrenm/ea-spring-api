package com.example.easpringapi.services;

import com.example.easpringapi.mappers.MovieMapper;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieService {

    private final MovieRepository movierepository;
    private final MovieMapper moviemapper;

    public MovieService(MovieRepository movierepository, MovieMapper moviemapper) {
        this.movierepository = movierepository;
        this.moviemapper = moviemapper;
    }

    public Movie findById(Integer integer) {
        return movierepository.findById(integer).get();
    }

    public Collection<Movie> findAll() {
        return movierepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        return movierepository.save(movie);
    }

    public Movie update(Movie movie) {
        return movierepository.save(movie);
    }

    public Movie delete(Movie movie) {
        return movierepository.save(movie);
    }

}

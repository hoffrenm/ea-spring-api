package com.example.easpringapi.services;

import com.example.easpringapi.mappers.MovieMapper;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.models.Character;

import com.example.easpringapi.repositories.MovieRepository;
import com.example.easpringapi.repositories.CharacterRepository;
import org.springframework.stereotype.Service;
import com.example.easpringapi.exceptions.MovieNotFoundException;


import java.util.Collection;
import java.util.HashSet;

/**
 * Service that encapsulates business logic of Movie
 */

@Service
public class MovieService {

    private final MovieRepository movierepository;
    private final MovieMapper moviemapper;
    private final CharacterRepository characterrepository;

    public MovieService(MovieRepository movierepository, MovieMapper moviemapper, CharacterRepository characterrepository) {
        this.movierepository = movierepository;
        this.moviemapper = moviemapper;
        this.characterrepository = characterrepository;
    }

    public Movie findById(Integer id) {

        return movierepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
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

    public void updateCharacters(int movieId, int[] characterIds) throws RuntimeException {
        Movie movie = movierepository.findById(movieId).get();
        HashSet<Character> characters = new HashSet<>();

        for (int i = 0; i < characterIds.length; i++) {
            int id = characterIds[i];
            Character character = characterrepository.findById(id).get();
            characters.add(character);
        }
        movie.setCharacters(characters);
        movierepository.save(movie);
    }

    public Collection<Character> getCharactersInMovie(Integer movieId) {
        if (movierepository.existsById(movieId)) {
            return characterrepository.findAllByMovie(movieId);
        } else {
            throw new MovieNotFoundException(movieId);
        }
    }
}

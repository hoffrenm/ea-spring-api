package com.example.easpringapi.services;

import com.example.easpringapi.exceptions.FranchiseNotFoundException;
import com.example.easpringapi.mappers.CharacterMapper;
import com.example.easpringapi.mappers.FranchiseMapper;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.FranchiseRepository;
import com.example.easpringapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Service that encapsulates business logic of Franchise
 */

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;
    private final MovieRepository movieRepository;
    private final CharacterMapper characterMapper;


    public FranchiseService(FranchiseRepository repository, FranchiseMapper franchiseMapper, MovieRepository movieRepository, CharacterMapper characterMapper) {
        this.franchiseRepository = repository;
        this.franchiseMapper = franchiseMapper;
        this.movieRepository = movieRepository;
        this.characterMapper = characterMapper;

    }

    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    public Franchise add(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Franchise update(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

    public void updateMovies(int franchiseId, int[] movieIds) {
        Franchise franchise = franchiseRepository.findById(franchiseId).get();
        Set<Movie> moviesList = new HashSet<>();

        for (int i = 0; i < movieIds.length; i++) {
            int id = movieIds[i];
            Movie movie = movieRepository.findById(id).get();
            movie.setFranchise(franchise);
            moviesList.add(movie);
        }
        franchise.setMovies(moviesList);
        franchiseRepository.save(franchise);
    }

    public Collection<Movie> getMoviesInFranchise(Integer franchiseId) {
        if (franchiseRepository.existsById(franchiseId)) {
            return movieRepository.findAllByFranchise(franchiseId);
        } else {
            throw new FranchiseNotFoundException(franchiseId);
        }
    }

    public Set<Character> getCharactersInFranchise(Integer franchiseId) {
        Set<Movie> moviesInFranchise = franchiseRepository.findById(franchiseId).get().getMovies();
        Set<Character> charactersInFranchise = new HashSet<>();
        moviesInFranchise.forEach(s-> charactersInFranchise.addAll(s.getCharacters()));
        return charactersInFranchise;
    }
}

package com.example.easpringapi.mappers;

import com.example.easpringapi.dto.FranchiseDTO;
import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;

import com.example.easpringapi.services.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Set;

/**
 * Mapper class to transform Franchise model into DTO and
 * DTO into Franchise when working with incoming and
 * outgoing data transfer.
 */
@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {

    @Autowired
    protected MovieService movieService;

    /**
     * Transforms Franchise object into franchise DTO.
     *
     * @param franchise Franchise object
     * @return franchise as DTO
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToMoviesId")
    public abstract FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);

    /**
     * Transforms collection of Franchise objects into collection of DTOs.
     *
     * @param franchises collection of Franchise objects
     * @return collection of franchises as DTOs
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToMoviesId")
    public abstract Collection<FranchiseDTO> franchiseToFranchiseDTO(Collection<Franchise> franchises);

    /**
     * Transforms franchise DTO into Franchise object.
     *
     * @param franchiseDTO franchise as DTO
     * @return Franchise object
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "movieIdsToMovies")
    public abstract Franchise franchiseDTOToFranchise(FranchiseDTO franchiseDTO);

    /**
     * Maps collection of Movies into collection
     * of Integers that represent ids of the movies.
     *
     * @param source set of movies
     * @return set of movies ids
     */
    @Named(value = "moviesToMoviesId")
    Set<Integer> map(Set<Movie> source){
        if(source == null) return null;
        return source.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }

    /**
     * Maps collection of ids into collection of movies
     * of corresponding ids.
     *
     * @param source set of ids
     * @return set of movies
     */
    @Named("movieIdsToMovies")
    Set<Movie> mapIdsToMovies(Set<Integer> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(i -> movieService.findById(i))
                .collect(Collectors.toSet());
    }
}

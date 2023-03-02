package com.example.easpringapi.mappers;

import com.example.easpringapi.dto.CharacterDTO;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.services.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper class to transform Character model into DTO and
 * DTO into Character when working with incoming and
 * outgoing data transfer.
 */
@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    @Autowired
    protected MovieService movieService;

    /**
     * Transforms Character object into character DTO.
     *
     * @param character Character object
     * @return character as DTO
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDTO(Character character);

    /**
     * Transforms collection of Character objects into collection of DTOs.
     *
     * @param characters collection of Character objects
     * @return collection of characters as DTOs
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract Collection<CharacterDTO> characterToCharacterDTO(Collection<Character> characters);


    /**
     * Transforms character DTO into Character object.
     *
     * @param characterDTO character as DTO
     * @return Character object
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "movieIdsToMovies")
    public abstract Character characterDTOToCharacter(CharacterDTO characterDTO);

    /**
     * Maps collection of Movies into collection
     * of Integers that represent ids of the movies.
     *
     * @param source set of movies
     * @return set of movies ids
     */
    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(Movie::getId)
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

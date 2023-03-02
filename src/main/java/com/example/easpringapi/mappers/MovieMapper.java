package com.example.easpringapi.mappers;

import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.dto.MovieDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper class to transform Movie model into DTO and
 * DTO into Movie when working with incoming and
 * outgoing data transfer.
 */
@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    /**
     * Transforms Movie object into movie DTO.
     *
     * @param movie Movie object
     * @return movie as DTO
     */
    @Mapping(target = "characters", source = "characters", qualifiedByName = "charactersToCharactersId")
    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchiseToFranchiseId")
    public abstract MovieDTO movieToMovieDTO(Movie movie);

    /**
     * Transforms collection of Movie objects into collection of DTOs.
     *
     * @param movies collection of Movie objects
     * @return collection of movies as DTOs
     */
    @Mapping(target = "character", source = "character", qualifiedByName = "charactersToCharactersId")
    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchiseToFranchiseId")
    public abstract Collection<MovieDTO> movieToMovieDTO(Collection<Movie> movies);

    /**
     * Transforms movie DTO into Movie object.
     *
     * @param movieDTO movie as DTO
     * @return Movie object
     */
    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchiseIdToFranchise")
    @Mapping(target = "characters", source = "characters", qualifiedByName = "characterIdsToCharacters")
    public abstract Movie movieDTOToMovie(MovieDTO movieDTO);

    /**
     * Maps collection of Character into collection
     * of Integers that represent ids of the characters.
     *
     * @param value set of characters
     * @return set of character ids
     */
    @Named(value = "charactersToCharactersId")
    Set<Integer> map(Set<Character> value) {
        if (value == null) return null;
        return value.stream()
                .map(Character::getId)
                .collect(Collectors.toSet());
    }

    /**
     * Map Franchise object into its id.
     *
     * @param value Franchise object
     * @return id of the franchise
     */
    @Named(value = "franchiseToFranchiseId")
    Integer map(Franchise value) {
        if (value == null)
            return 0;
        return value.getId();
    }

    @Named(value = "franchiseIdToFranchise")
    Franchise map(Integer id) {
        return null;
    }

    @Named(value = "characterIdsToCharacters")
    Set<Character> mapIdsToCharacters(Set<Integer> value) {
        return null;
    }
}

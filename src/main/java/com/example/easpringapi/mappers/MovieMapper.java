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

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Mapping(target = "characters", source = "characters", qualifiedByName = "charactersToCharactersId")
    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchiseToFranchiseId")
    public abstract MovieDTO movieToMovieDTO(Movie movie);

    @Mapping(target = "character", source = "character", qualifiedByName = "charactersToCharactersId")
    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchiseToFranchiseId")
    public abstract Collection<MovieDTO> movieToMovieDTO(Collection<Movie> movie);

    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchiseIdToFranchise")
    @Mapping(target = "characters", source = "characters", qualifiedByName = "characterIdsToCharacters")
    public abstract Movie movieDTOToMovie(MovieDTO movieDTO);

    @Named(value = "charactersToCharactersId")
    Set<Integer> map(Set<Character> value) {
        if (value == null) return null;
        return value.stream()
                .map(Character::getId)
                .collect(Collectors.toSet());
    }

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

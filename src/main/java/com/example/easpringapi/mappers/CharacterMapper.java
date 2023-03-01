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

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    @Autowired
    protected MovieService movieService;

    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDTO(Character character);

    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract Collection<CharacterDTO> characterToCharacterDTO(Collection<Character> characters);

    @Mapping(target = "movies", source = "movies", qualifiedByName = "movieIdsToMovies")
    public abstract Character characterDTOToCharacter(CharacterDTO dto);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(Movie::getId)
                .collect(Collectors.toSet());
    }

    @Named("movieIdsToMovies")
    Set<Movie> mapIdsToMovies(Set<Integer> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(i -> movieService.findById(i))
                .collect(Collectors.toSet());
    }
}

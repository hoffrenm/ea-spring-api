package com.example.easpringapi.mappers;

import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.dto.MovieDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
public abstract class MovieMapper {

    @Mapping(target = "character", source = "character.id", qualifiedByName = "charactersToCharactersId")
    @Mapping(target = "franchise", source = "franchise.id", qualifiedByName = "franchiseToFranchiseId")

    //public abstract MovieDTO movieToMovieDTO(Movie movie);

    public abstract Collection<MovieDTO> movieToMovieDTO(Collection<MovieDTO> moviedto);
    //public abstract Collection<Movie> movieToMovie(Collection<Movie> movie);

    public abstract Movie movieDTOToMovie(MovieDTO movieDTO);

    //public abstract Movie movieToMovieDtoO(Movie movie);


    @Named(value = "charactersToCharactersId")
     Collection<Integer> map(Collection<Character> value) {
        if(value == null) return null;
        return value.stream()
             .map(s -> s.getId())
            .collect(Collectors.toSet());
    }

    @Named(value = "franchiseToFranchiseId")
    Integer map(Franchise value) {
        if (value == null)
            return 0;
        return value.getId();
    }

}

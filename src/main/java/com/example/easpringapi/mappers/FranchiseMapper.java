package com.example.easpringapi.mappers;

import com.example.easpringapi.dto.FranchiseDTO;
import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {

    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToMoviesId")
    public abstract FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);

    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToMoviesId")
    public abstract Collection<FranchiseDTO> franchiseToFranchiseDTO(Collection<Franchise> franchise);

   // @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToMoviesId")
   // public abstract Franchise franchiseDtoToFranchise(FranchiseDTO franchiseDTO);


    @Named(value = "moviesToMoviesId")
    Set<Integer> map(Set<Movie> value){
        if(value == null) return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }

    @Named(value = "movieToMovie")
    Movie map(Integer id) {
        return null;
    }
}

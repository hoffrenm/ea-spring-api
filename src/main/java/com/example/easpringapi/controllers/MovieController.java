package com.example.easpringapi.controllers;

import com.example.easpringapi.dto.CharacterDTO;
import com.example.easpringapi.dto.FranchiseDTO;
import com.example.easpringapi.mappers.CharacterMapper;
import com.example.easpringapi.mappers.MovieMapper;
import com.example.easpringapi.dto.MovieDTO;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.services.CharacterService;
import com.example.easpringapi.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movies") //maybe
public class MovieController {

    private final MovieService movieservice;
    private final MovieMapper moviemapper;
    private final CharacterMapper characterMapper;

    public MovieController(MovieService movieservice, MovieMapper moviemapper, CharacterMapper characterMapper) {
        this.movieservice = movieservice;
        this.moviemapper = moviemapper;
        this.characterMapper = characterMapper;
    }

    //get by id
    @GetMapping("{id}")
    @Operation(summary = "Get movie by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Movie fetched successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Movie does not exist with supplied ID",
                    content = @Content)
    })
    public ResponseEntity<MovieDTO> findById(@PathVariable int id) {
        MovieDTO movieDTO = moviemapper.movieToMovieDTO(movieservice.findById(id));

        return ResponseEntity.ok(movieDTO);
    }

    //get all
    @GetMapping
    @Operation(summary = "Get All Movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All movies fetched successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        Collection<MovieDTO> movies = moviemapper.movieToMovieDTO(movieservice.findAll());

        return ResponseEntity.ok(movies);
    }

    //create
    @PostMapping
    @Operation(summary = "Add a new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Request body missing or invalid parameters",
                    content = @Content)
    })
    public ResponseEntity add(@RequestBody MovieDTO movieDto) {
        Movie movieToPost = movieservice.addMovie(moviemapper.movieDTOToMovie(movieDto));
        URI location = URI.create("movies/" + movieToPost.getId());

        return ResponseEntity.created(location).build();
    }

    //update
    @PutMapping("{id}")
    @Operation(summary = "Update an existing movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Movie updated successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    public ResponseEntity updateMovie(@RequestBody MovieDTO moviedto, @PathVariable int id) {
        if (id == moviedto.getId()) {
            movieservice.update(
                    moviemapper.movieDTOToMovie(moviedto)
            );
            return ResponseEntity.noContent().build();
        }

        return null;
    }

    //delete
    @DeleteMapping("{id}")
    @Operation(summary = "Delete a given movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie deleted successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Movie with id does not exist",
                    content = @Content)
    })
    public ResponseEntity<Void>deleteById(@PathVariable int id) {
            movieservice.deleteById(id);

            return ResponseEntity.noContent().build();
        }


    //update a character in a movie
    @PutMapping("{id}/characters")
    @Operation(summary = "update a character in a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update successful",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    public ResponseEntity updateCharacter(@PathVariable int id, @RequestBody int[] charactersIds) {
        movieservice.updateCharacters(id, charactersIds);

        return ResponseEntity.noContent().build();
    }

    // GET: /api/v1/movies/1/characters
    @Operation(summary = "Get all characters in movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Movie not found",
                    content = @Content)
    })
    @GetMapping("{id}/characters")
    public ResponseEntity<Collection<CharacterDTO>> getCharactersInMovie(@PathVariable Integer id) {
        Collection<CharacterDTO> characters = characterMapper.characterToCharacterDTO(movieservice.getCharactersInMovie(id));

        return ResponseEntity.ok(characters);
    }
}
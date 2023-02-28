package com.example.easpringapi.controllers;

import com.example.easpringapi.mappers.MovieMapper;
import com.example.easpringapi.models.dto.MovieDTO;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.MovieRepository;
import com.example.easpringapi.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
public class MovieController {

    private final MovieService movieservice;
    private final MovieMapper moviemapper;

    public MovieController(MovieService movieservice, MovieMapper moviemapper) {
        this.movieservice = movieservice;
        this.moviemapper = moviemapper;
    }

    //get by id
    @GetMapping("{id}")
    @Operation(summary = "Get movie by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Movie fetched succesfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))})
    })

    public ResponseEntity findById(@PathVariable int id) {
            return ResponseEntity.ok(
                moviemapper.movieToMovieDTO(
                        movieservice.findAll()));

    }


    //get all
    @GetMapping
    @Operation(summary = "Get All Movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All movies fetched succesfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))})
    })

    public ResponseEntity<Collection<MovieDTO>> getAll() {
        Collection<MovieDTO> movies = moviemapper.movieToMovieDTO(
                movieservice.findAll()
        );
        return ResponseEntity.ok(movies);
    }

    //create
    @PostMapping
    @Operation(summary = "Add a new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
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
                    description = "Movie updated succesfully",
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
            @ApiResponse( responseCode =  "204",
                    description = "Movie deleted succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))})
            })

    public ResponseEntity deleteById(@RequestBody MovieDTO moviedto, @PathVariable int id) {
        if(id == moviedto.getId())
            movieservice.delete(
                    moviemapper.movieDTOToMovie(moviedto)
            );
            return ResponseEntity.noContent().build();
        }


        //delete character from a movie

}

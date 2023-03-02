package com.example.easpringapi.controllers;

import com.example.easpringapi.dto.CharacterDTO;
import com.example.easpringapi.dto.MovieDTO;
import com.example.easpringapi.mappers.CharacterMapper;
import com.example.easpringapi.mappers.MovieMapper;
import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.dto.FranchiseDTO;
import com.example.easpringapi.mappers.FranchiseMapper;
import com.example.easpringapi.services.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    public FranchiseController(FranchiseService service, FranchiseMapper franchiseMapper, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.franchiseService = service;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    // GET: /api/v1/franchises
    @Operation(summary = "Get all franchises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Collection<FranchiseDTO>> getAll() {
        Collection<FranchiseDTO> franchises = franchiseMapper.franchiseToFranchiseDTO(franchiseService.findAll());

        return ResponseEntity.ok(franchises);
    }

    // GET: /api/v1/franchises/1
    @Operation(summary = "Get a franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Franchise does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<FranchiseDTO> getById(@PathVariable int id) {
        FranchiseDTO franchiseDTO = franchiseMapper.franchiseToFranchiseDTO(
                franchiseService.findById(id)
        );

        return ResponseEntity.ok(franchiseDTO);
    }

    // POST: /api/v1/franchises
    @Operation(summary = "Create a new franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Request body missing or invalid parameters",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<FranchiseDTO> add(@RequestBody FranchiseDTO franchiseDTO) {
        try {
            Franchise createdFranchise = franchiseService.add(franchiseMapper.franchiseDTOToFranchise(franchiseDTO));
            FranchiseDTO createdCharacterDTO = franchiseMapper.franchiseToFranchiseDTO(createdFranchise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacterDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT: /api/v1/franchises/1
    @Operation(summary = "Update a franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Franchise franchise, @PathVariable int id) {
        // Validates if body is correct
        if (id != franchise.getId()) {
            return ResponseEntity.badRequest().build();
        }

        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }

    // DELETE: /api/v1/franchises/1
    @Operation(summary = "Delete a franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Franchise with id does not exist",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        franchiseService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    //UPDATE movie in franchise
    @Operation(summary = "Update a movie in a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    @PutMapping("{id}/movies")
    public ResponseEntity updateMovie(@PathVariable int id, @RequestBody int[] movieIds) {
        franchiseService.updateMovies(id, movieIds);

        return ResponseEntity.noContent().build();
    }

    // GET: /api/v1/franchises/1/movies
    @Operation(summary = "Get all movies in franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found",
                    content = @Content)
    })
    @GetMapping("{id}/movies")
    public ResponseEntity<Collection<MovieDTO>> getMoviesInFranchise(@PathVariable Integer id) {
        Collection<MovieDTO> movies = movieMapper.movieToMovieDTO(franchiseService.getMoviesInFranchise(id));

        return ResponseEntity.ok(movies);
    }

    // GET: /api/v1/franchises/{id}/characters
    @Operation(summary = "Get characters in a franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Charecters franchise id not found",
                    content = @Content)
    })

    @GetMapping("{id}/characters")
    public ResponseEntity getCharactersInFranchise(@PathVariable Integer id){
        return ResponseEntity.ok(characterMapper.characterToCharacterDTO(franchiseService.getCharactersInFranchise(id)));}
}

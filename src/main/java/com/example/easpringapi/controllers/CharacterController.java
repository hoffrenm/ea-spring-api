package com.example.easpringapi.controllers;

import com.example.easpringapi.dto.CharacterDTO;
import com.example.easpringapi.dto.FranchiseDTO;
import com.example.easpringapi.mappers.CharacterMapper;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    // GET: /api/v1/characters
    @Operation(summary = "Get all characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))})
    })
    @GetMapping
    public ResponseEntity<Collection<CharacterDTO>> getAll() {
        Collection<CharacterDTO> characters = characterMapper.characterToCharacterDTO(
                characterService.findAll()
        );

        return ResponseEntity.ok(characters);
    }

    // GET: /api/v1/characters/1
    @Operation(summary = "Get a character by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Character does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<CharacterDTO> getById(@PathVariable int id) {
        CharacterDTO charDTO = characterMapper.characterToCharacterDTO(
                characterService.findById(id)
        );

        return ResponseEntity.ok(charDTO);
    }

    // POST: /api/v1/characters
    @Operation(summary = "Create a new character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Request body missing or invalid parameters",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CharacterDTO> add(@RequestBody Character character) {
        try {
            Character createdCharacter = characterService.add(character);
            CharacterDTO createdCharacterDTO = characterMapper.characterToCharacterDTO(createdCharacter);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacterDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT: /api/v1/characters/1
    @Operation(summary = "Update a character by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Mismatching IDs between request body and uri",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Character character, @PathVariable int id) {
        // Validates if body is correct
        if (id != character.getId()) {
            return ResponseEntity.badRequest().build();
        }

        characterService.update(character);
        return ResponseEntity.noContent().build();
    }

    // DELETE: /api/v1/characters/1
    @Operation(summary = "Delete a character by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

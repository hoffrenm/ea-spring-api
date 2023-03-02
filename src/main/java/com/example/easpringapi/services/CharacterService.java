package com.example.easpringapi.services;

import com.example.easpringapi.exceptions.CharacterNotFoundException;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service that encapsulates business logic of Character
 */

@Service

public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository repository) {
        this.characterRepository = repository;
    }

    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public Character add(Character character) {
        return characterRepository.save(character);
    }

    public void update(Character character) {
        int id = character.getId();

        if (characterRepository.existsById(id)) {
            characterRepository.save(character);
        } else {
            throw new CharacterNotFoundException(id);
        }
    }

    public void deleteById(Integer id) {
        Character character = characterRepository.findById(id).get();

        // We need to remove character from every movie
        // they appear because it doesn't happen automatically
        for (Movie movie : character.getMovies()) {
            movie.getCharacters().remove(character);
        }

        characterRepository.delete(character);
    }
}

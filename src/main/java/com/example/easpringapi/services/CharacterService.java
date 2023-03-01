package com.example.easpringapi.services;

import com.example.easpringapi.exceptions.CharacterNotFoundException;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    public Character update(Character character) {
        return characterRepository.save(character);
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

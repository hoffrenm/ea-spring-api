package com.example.easpringapi.services;

import com.example.easpringapi.models.Character;
import com.example.easpringapi.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CharacterService {

    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public Collection<Character> findAll() {
        return repository.findAll();
    }

    public Character findById(Integer id) {
        return repository.findById(id).get();
    }

    public Character add(Character character) {
        return repository.save(character);
    }

    public Character update(Character character) {
        return repository.save(character);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

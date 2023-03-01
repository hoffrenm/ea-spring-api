package com.example.easpringapi.services;

import com.example.easpringapi.exceptions.FranchiseNotFoundException;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.FranchiseRepository;

import java.util.Collection;

public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository repository) {
        this.franchiseRepository = repository;
    }

    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    public Franchise add(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Franchise update(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

}

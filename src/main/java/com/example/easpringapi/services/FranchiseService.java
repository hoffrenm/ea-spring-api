package com.example.easpringapi.services;

import com.example.easpringapi.exceptions.FranchiseNotFoundException;
import com.example.easpringapi.mappers.FranchiseMapper;
import com.example.easpringapi.models.Character;
import com.example.easpringapi.models.Franchise;
import com.example.easpringapi.models.Movie;
import com.example.easpringapi.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    public FranchiseService(FranchiseRepository repository, FranchiseMapper franchiseMapper) {
        this.franchiseRepository = repository;
        this.franchiseMapper = franchiseMapper;
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

package com.example.easpringapi.repositories;

import com.example.easpringapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT * FROM movie WHERE franchise_id = ?1", nativeQuery = true)
    Set<Movie> findAllByFranchise(Integer franchiseId);
}

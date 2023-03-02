package com.example.easpringapi.repositories;

import com.example.easpringapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * encapsulates data access of Character class
 */

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    @Query(value = "SELECT * FROM character " +
            "JOIN movie_character ON character.id = movie_character.character_id " +
            "WHERE movie_id = ?1", nativeQuery = true)
    Set<Character> findAllByMovie(Integer movieId);
}

package com.example.easpringapi.repositories;

import com.example.easpringapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * encapsulates data access of Franchise class
 */


@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}

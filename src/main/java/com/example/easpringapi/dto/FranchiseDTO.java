package com.example.easpringapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * Data transfer object that represents Franchise model
 * for outgoing data transfer purposes.
 */
@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movies;

}

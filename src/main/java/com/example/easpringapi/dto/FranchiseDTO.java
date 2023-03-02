package com.example.easpringapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Data

public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movies;

}

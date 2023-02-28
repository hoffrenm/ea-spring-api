package com.example.easpringapi.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CharacterDTO {
    private int id;
    private String name;
    private String alias;
    private String gender;
    private String pictureUrl;
    private Set<Integer> movies;
}

package com.example.easpringapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Character {
    
    @Id
    private Long id;
}

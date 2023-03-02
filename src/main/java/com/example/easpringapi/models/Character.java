package com.example.easpringapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String alias;

    @Column(length = 32)
    private String gender;

    @Column(length = 128)
    private String pictureUrl;

    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;
}

package com.example.easpringapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int releaseYear;

    @Column(nullable = false)
    private String director;

    @Column
    private String picture;

    @Column
    private String trailer;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToMany
    @JoinTable(
            name = "movie_character",
            joinColumns={@JoinColumn(name="movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )

    //Set; allows no duplicates
    private Set<Character> characters;

    //no arguments constructor
    public Movie() {}

    public Movie(String title, String genre, int releaseYear, String director, Franchise franchise) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.franchise = franchise
    }

}

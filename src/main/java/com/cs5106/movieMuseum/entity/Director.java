package com.cs5106.movieMuseum.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "director", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();

    public Director(String firstName, String lastName, int age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
        movie.setDirector(this);
    }

    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.setDirector(null);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}

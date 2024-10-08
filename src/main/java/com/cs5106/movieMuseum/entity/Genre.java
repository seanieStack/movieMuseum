package com.cs5106.movieMuseum.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String genreName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "genres", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();

    public Genre(String genreName) {
        super();
        this.genreName = genreName;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
        movie.getGenres().add(this);
    }

    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.getGenres().remove(this);
    }

}

package com.cs5106.movieMuseum.domain.repository;

import com.cs5106.movieMuseum.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import com.cs5106.movieMuseum.domain.entity.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAllByGenreName(String genreName);
}

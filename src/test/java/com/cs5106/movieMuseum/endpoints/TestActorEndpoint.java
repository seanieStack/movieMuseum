package com.cs5106.movieMuseum.endpoints;

import com.cs5106.movieMuseum.MovieMuseumApplication;
import com.cs5106.movieMuseum.controller.MovieController;
import com.cs5106.movieMuseum.entity.Actor;
import com.cs5106.movieMuseum.entity.Movie;
import com.cs5106.movieMuseum.repository.ActorRepository;
import com.cs5106.movieMuseum.repository.MovieRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MovieMuseumApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestActorEndpoint {

    @Autowired
    private MovieController movieController;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetActors_returnsAllActor() throws Exception {

        //given there are movies and actors
        createMovie("StarWars", 1995, 10.0);
        createMovie("Hobbit", 2000, 5.0);
        createActor("Person1", "Man1", 1, Collections.singletonList("StarWars"));
        createActor("Person2", "Man2", 2, Collections.singletonList("StarWars"));
        createActor("Person3", "Man3", 3, Arrays.asList("StarWars", "Hobbit"));
        createActor("Person4", "Man4", 4, Collections.singletonList("Hobbit"));
        createActor("Person5", "Man5", 5, Collections.singletonList("Hobbit"));

        //when getting all actors
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/actors")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        MvcResult mvcResult = result.andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<Actor> actors = mapper.readValue(responseContent, new TypeReference<>() {
        });

        //then assert that there are the correct amount
        Assertions.assertEquals(5, actors.size(), "Unexpected Number of actors");
    }

    private void createActor(String first, String second, int age, List<String> relatedMovie) {
        Actor actor = new Actor(first, second, age);

        actorRepository.save(actor);

        for (String movie : relatedMovie){
            Movie movie1 = movieController.getMovieByTitle(movie);
            actor.addMovie(movie1);
            movieRepository.save(movie1);
        }

        actorRepository.save(actor);
    }

    private void createMovie(String name, int year, double rating) {
        Movie movie = new Movie(name, year, rating);
        movieRepository.save(movie);
    }
}

package com.Assignment.SpringBootAssignment;

import com.Assignment.SpringBootAssignment.VO.ResponceTemplate;
import com.Assignment.SpringBootAssignment.controller.MovieController;
import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
  class MovieControllerTest {


    @BeforeEach
      void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
      MovieController movieController;

    @Mock
      MovieServices movieServices;

    @Test
      void testFindMovieById() {

        int movieId = 1;
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        ResponceTemplate expectedResponse = new ResponceTemplate(movie);

        Mockito.when(movieServices.findMoviewithDetailsById(movieId)).thenReturn(expectedResponse);

        ResponceTemplate actualResponse = movieController.findMovieById(movieId);

        Assert.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(movieServices).findMoviewithDetailsById(movieId);
    }

    @Test
      void testFindAllMovies() {

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie());
        expectedMovies.add(new Movie());

        Mockito.when(movieServices.findAllMovies()).thenReturn(expectedMovies);

        List<Movie> actualMovies = movieController.findAllMovies();

        Assert.assertEquals(expectedMovies, actualMovies);
        Mockito.verify(movieServices).findAllMovies();
    }

    @Test
      void testPostMovie() {

        Movie movie = new Movie();
        movie.setMovieId(1);

        Mockito.when(movieServices.save(Mockito.any(Movie.class))).thenReturn(movie);

        Movie savedMovie = movieController.postMovie(movie);

        Assert.assertEquals(movie, savedMovie);
        Mockito.verify(movieServices).save(movie);
    }

    @Test
      void testPutMapping() {

        Movie movie = new Movie();
        movie.setMovieId(1);

        Mockito.when(movieServices.save(Mockito.any(Movie.class))).thenReturn(movie);

        Movie updatedMovie = movieController.putMapping(movie);

        Assert.assertEquals(movie, updatedMovie);
        Mockito.verify(movieServices).save(movie);
    }

    @Test
      void testDeleteMovie() {

        int movieId = 1;

        String response = movieController.deleteMovie(movieId);

        Assert.assertEquals("the deleted movie id : " + movieId, response);
        Mockito.verify(movieServices).delete(movieId);
    }

    @Test
      void testMoviePagination() {

        int pageNumber = 1;
        int pageSize = 10;
        String sortName = "movieName";
        Page<Movie> expectedPage = new PageImpl<>(new ArrayList<>());

        Mockito.when(movieServices.pagination(pageNumber, pageSize, sortName)).thenReturn(expectedPage);

        Page<Movie> actualPage = movieController.moviepagination(pageNumber, pageSize, sortName);

        Assert.assertEquals(expectedPage, actualPage);
        Mockito.verify(movieServices).pagination(pageNumber, pageSize, sortName);
    }
}

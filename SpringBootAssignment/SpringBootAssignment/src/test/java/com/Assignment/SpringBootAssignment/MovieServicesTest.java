package com.Assignment.SpringBootAssignment;

import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.repository.MovieRepository;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieServicesTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServices movieServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetMovieById() {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Movie1");

        when(movieRepository.findByMovieId(1)).thenReturn(movie);

        Movie result = movieServices.movieName(1);

        assertEquals(movie, result);
        verify(movieRepository, times(1)).findByMovieId(1);
    }

    @Test
     void testGetAllMovies() {
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("Movie1");

        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("Movie2");

        List<Movie> movieList = Arrays.asList(movie1, movie2);

        when(movieRepository.findAll()).thenReturn(movieList);

        List<Movie> result = movieServices.moviesName();

        assertEquals(movieList, result);
        verify(movieRepository, times(1)).findAll();
    };

    @Test
     void testSaveMovie() {
        Movie movie = new Movie();
        movie.setMovieName("New Movie");
        when(movieRepository.save(movie)).thenReturn(movie);
        Movie result = movieServices.save(movie);
        assertEquals(movie, result);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
     void testDeleteMovie() {
        int id = 1;
        Movie movie = new Movie();
        movie.setMovieId(id);
        movie.setMovieName("Movie1");
        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        movieServices.delete(id);
        verify(movieRepository, times(1)).deleteById(id);
    }
}

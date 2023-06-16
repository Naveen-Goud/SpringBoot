package com.Assignment.testcases;

import com.Assignment.SpringBootAssignment.VO.MovieDetails;
import com.Assignment.SpringBootAssignment.VO.ResponceTemplate;
import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.exceptions.MovieNotFoundException;
import com.Assignment.SpringBootAssignment.repository.MovieRepository;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MovieServicesTest {

    @InjectMocks
    private MovieServices movieServices;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private RestTemplate restTemplate;


    @Before
    public void setup() {
        movieServices.setRestTemplate(restTemplate);
    }
    @Test
    public void testFindMoviewithDetailsById() {
        // Arrange
        int movieId = 1;
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        MovieDetails movieDetails = new MovieDetails();
        ResponceTemplate expectedResponse = new ResponceTemplate( movie,movieDetails);

        Mockito.when(movieRepository.findByMovieId(movieId)).thenReturn(movie);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(MovieDetails.class)))
                .thenReturn(movieDetails);

        // Act
        ResponceTemplate result = movieServices.findMoviewithDetailsById(movieId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);

        Mockito.verify(movieRepository, Mockito.times(1)).findByMovieId(movieId);
        Mockito.verify(restTemplate, Mockito.times(1))
                .getForObject("http://localhost:9002/MovieDetails/" + movieId, MovieDetails.class);
    }

    @Test(expected = MovieNotFoundException.class)
    public void testFindMoviewithDetailsById_MovieNotFound() {
        // Arrange
        int movieId = 1;
        MovieRepository movieRepositoryMock = mock(MovieRepository.class);
        ResponceTemplate expectedResponse = new ResponceTemplate();

        Mockito.when(movieRepository.findByMovieId(movieId)).thenReturn(null);

        // Act
        ResponceTemplate result = movieServices.findMoviewithDetailsById(movieId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);

        Mockito.verify(movieRepository, Mockito.times(1)).findByMovieId(movieId);
        Mockito.verifyNoInteractions(restTemplate);
    }

    @Test
    public void testFindMoviewithDetailsById_RestTemplateReturnsNull() {
        // Arrange
        int movieId = 1;
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        ResponceTemplate expectedResponse = new ResponceTemplate(movie,null);

        Mockito.when(movieRepository.findByMovieId(movieId)).thenReturn(movie);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(MovieDetails.class)))
                .thenReturn(null);

        // Act
        ResponceTemplate result = movieServices.findMoviewithDetailsById(movieId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);

        Mockito.verify(movieRepository, Mockito.times(1)).findByMovieId(movieId);
        Mockito.verify(restTemplate, Mockito.times(1))
                .getForObject("http://localhost:9002/MovieDetails/" + movieId, MovieDetails.class);
    }

    @Test
    public void testFindAllMovies() {
        // Arrange
        List<Movie> expectedMovies = Arrays.asList(new Movie(), new Movie());
        Mockito.when(movieRepository.findAll()).thenReturn(expectedMovies);

        // Act
        List<Movie> result = movieServices.findAllMovies();

        // Assert
        assertNotNull(result);
        assertEquals(expectedMovies, result);

        Mockito.verify(movieRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testSave() {
        // Arrange
        Movie movie = new Movie();
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);

        // Act
        Movie result = movieServices.save(movie);

        // Assert
        assertNotNull(result);
        assertEquals(movie, result);

        Mockito.verify(movieRepository, Mockito.times(1)).save(movie);
    }

    @Test
    public void testDelete() {
        // Arrange
        int movieId = 1;

        // Act
        movieServices.delete(movieId);

        // Assert
        Mockito.verify(movieRepository, Mockito.times(1)).deleteById(movieId);
    }

    @Test
    public void testPagination_DefaultSortName() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 10;
        Sort.Direction defaultSortDirection = Sort.Direction.ASC;
        String defaultSortName = "movieName";
        Pageable expectedPageable = PageRequest.of(pageNumber, pageSize, defaultSortDirection, defaultSortName);
        Page<Movie> expectedPage = new PageImpl<>(Collections.emptyList());

        Mockito.when(movieRepository.findAll(expectedPageable)).thenReturn(expectedPage);

        // Act
        Page<Movie> result = movieServices.pagination(pageNumber, pageSize, null);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPage, result);

        Mockito.verify(movieRepository, Mockito.times(1)).findAll(expectedPageable);
    }

    @Test
    public void testPagination_CustomSortName() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 10;
        Sort.Direction defaultSortDirection = Sort.Direction.ASC;
        String customSortName = "customSort";
        Pageable expectedPageable = PageRequest.of(pageNumber, pageSize, defaultSortDirection, customSortName);
        Page<Movie> expectedPage = new PageImpl<>(Collections.emptyList());

        Mockito.when(movieRepository.findAll(expectedPageable)).thenReturn(expectedPage);

        // Act
        Page<Movie> result = movieServices.pagination(pageNumber, pageSize, customSortName);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPage, result);

        Mockito.verify(movieRepository, Mockito.times(1)).findAll(expectedPageable);
    }
    @Test
    public void testFindMoviewithDetailsById_ValidId_ReturnsResponceTemplateWithMovieAndMovieDetails() {
        // Arrange
        int id = 1;
        Movie movie = new Movie(id, "Movie 1");
        MovieDetails movieDetails = new MovieDetails(id, "Movie 1 Details");

        Mockito.when(movieRepository.findByMovieId(id)).thenReturn(movie);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(MovieDetails.class))).thenReturn(movieDetails);

        // Act
        ResponceTemplate result = movieServices.findMoviewithDetailsById(id);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(movie, result.getMovie());
        Assertions.assertEquals(movieDetails, result.getMovieDetails());
    }

    @Test(expected = MovieNotFoundException.class)
    public void testFindMoviewithDetailsById_InvalidId_ReturnsResponceTemplateWithNullMovieAndMovieDetails() {
        // Arrange
        int id = 100; // Assuming it doesn't exist
        Mockito.when(movieRepository.findByMovieId(id)).thenReturn(null);

        // Act
        ResponceTemplate result = movieServices.findMoviewithDetailsById(id);

        // Assert
       // Assertions.assertNotNull(result);
        Assertions.assertNull(result.getMovie());
        Assertions.assertNull(result.getMovieDetails());
    }

    @Test
    public void testFindMoviewithDetailsById_ExceptionThrown_HandlesExceptionGracefully() {
        // Arrange
        int id = 1;
        Mockito.when(movieRepository.findByMovieId(id)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Assertions.assertThrows(Exception.class, () -> {
            movieServices.findMoviewithDetailsById(id);
        });
    }



}

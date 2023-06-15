package com.Assignment.SpringBootAssignment;

import com.Assignment.SpringBootAssignment.exceptions.MovieErrorResponse;
import com.Assignment.SpringBootAssignment.exceptions.MovieNotFoundException;
import com.Assignment.SpringBootAssignment.exceptions.MovieRestExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieRestExceptionHandlerTest {

    private MovieRestExceptionHandler exceptionHandler;

    @BeforeEach
    public void setup() {
        exceptionHandler = new MovieRestExceptionHandler();
    }

    @Test
    public void handleMovieNotFoundException_ShouldReturnNotFoundResponse() {
        // Arrange
        MovieNotFoundException exception = new MovieNotFoundException("Movie not found");

        // Act
        ResponseEntity<MovieErrorResponse> response = exceptionHandler.handleException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Movie not found", response.getBody().getMessage());
    }

    @Test
    public void handleException_ShouldReturnBadRequestResponse() {
        // Arrange
        Exception exception = new Exception("Bad request");

        // Act
        ResponseEntity<MovieErrorResponse> response = exceptionHandler.handleException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", response.getBody().getMessage());
    }
}

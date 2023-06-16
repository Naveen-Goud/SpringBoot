package com.Assignment.SpringBootAssignment;

import com.Assignment.SpringBootAssignment.exceptions.MovieErrorResponse;
import com.Assignment.SpringBootAssignment.exceptions.MovieNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieErrorResponseTest {

    @Test
    public void testMovieErrorResponseConstructor() {
        // Arrange
        int expectedStatus = 404;
        String expectedMessage = "Movie not found";
        long expectedTimeStamp = System.currentTimeMillis();

        // Act
        MovieErrorResponse errorResponse = new MovieErrorResponse(expectedStatus, expectedMessage, expectedTimeStamp);

        // Assert
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedMessage, errorResponse.getMessage());
        assertEquals(expectedTimeStamp, errorResponse.getTimeStamp());
    }

    @Test
    public void testMovieErrorResponseGettersAndSetters() {
        // Arrange
        MovieErrorResponse errorResponse = new MovieErrorResponse();

        int expectedStatus = 500;
        String expectedMessage = "Internal Server Error";
        long expectedTimeStamp = System.currentTimeMillis();

        // Act
        errorResponse.setStatus(expectedStatus);
        errorResponse.setMessage(expectedMessage);
        errorResponse.setTimeStamp(expectedTimeStamp);

        // Assert
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedMessage, errorResponse.getMessage());
        assertEquals(expectedTimeStamp, errorResponse.getTimeStamp());
    }
    @Test
    public void testMovieNotFoundExceptionWithMessage() {
        // Arrange
        String expectedMessage = "Movie not found";

        // Act
        MovieNotFoundException exception = new MovieNotFoundException(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testMovieNotFoundExceptionWithMessageAndCause() {
        // Arrange
        String expectedMessage = "Movie not found";
        Throwable cause = new RuntimeException("Some cause");

        // Act
        MovieNotFoundException exception = new MovieNotFoundException(expectedMessage, cause);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testMovieNotFoundExceptionWithCause() {
        // Arrange
        Throwable cause = new RuntimeException("Some cause");

        // Act
        MovieNotFoundException exception = new MovieNotFoundException(cause);

        // Assert
        assertEquals(cause, exception.getCause());
    }
}

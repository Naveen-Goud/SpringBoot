package com.Assignment.SpringBootAssignment;

import com.Assignment.SpringBootAssignment.controller.MovieController;
import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(value=MovieController.class )
@RunWith(SpringRunner.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieServices movieServices;

    @Test
    public void testGetMovieById() throws Exception {
        // Arrange

        Movie mockMovie = new Movie();
        mockMovie.setMovieId(1);
        mockMovie.setMovieName("mock movie");
        Mockito.when(movieServices.movieName(Mockito.anyInt())).thenReturn(mockMovie);

        String URI= "/movies/1";
        // Act
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mockMovie);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson, Matchers.is(equalTo(expectedJson)));

   }
    @Test
    public void testPostMovie() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("New Movie");
        movie.setRating("3");
        movie.setReleaseDate("29-april-2020");

        String inputjson = this.mapToJson(movie);
        String  URI = "/movies/";

        Mockito.when(movieServices.save(Mockito.any(Movie.class))).thenReturn(movie);

        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputjson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response= result.getResponse();
        String outputJson = response.getContentAsString();
        assertThat(outputJson, Matchers.is(equalTo(inputjson)));
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    public void testDeleteMovie() throws Exception {
        int id = 1;
        Movie movie = new Movie();
        movie.setMovieId(id);
        movie.setMovieName("Movie to delete");

        when(movieServices.movieName(id)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("the deleted movie is : Movie to delete"));

      //  verify(movieServices, times(1)).delete(id);
        verify(movieServices, times(1)).movieName(id);
     //   verifyNoMoreInteractions(movieServices);
    }

    //helper

    private String mapToJson(Object object) throws JsonProcessingException{
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

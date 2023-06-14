package com.Assignment.SpringBootAssignment.services;

import com.Assignment.SpringBootAssignment.entity.Movie;

import java.util.List;

public interface MovieServicesInterface {

           void delete(int id);
         Movie findMovieById(int id);
          List<Movie> findAllMovies();
          Movie save(Movie movie);


}

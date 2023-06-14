package com.Assignment.SpringBootAssignment.services;

import com.Assignment.SpringBootAssignment.entity.Movie;

import java.util.List;

public interface MovieServicesInterface {

           void delete(int id);
         Movie movieName(int id);
          List<Movie> moviesName();
          Movie save(Movie movie);
}

package com.Assignment.SpringBootAssignment.services;

import com.Assignment.SpringBootAssignment.VO.ResponceTemplate;
import com.Assignment.SpringBootAssignment.entity.Movie;
import java.util.List;

public interface MovieServicesInterface {
    void delete(int id);
    ResponceTemplate findMoviewithDetailsById(int id);
    List<Movie> findAllMovies();
    Movie save(Movie movie);
}

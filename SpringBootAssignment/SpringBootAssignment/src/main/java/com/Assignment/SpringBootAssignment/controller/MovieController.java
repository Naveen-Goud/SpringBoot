package com.Assignment.SpringBootAssignment.controller;

import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")

public class MovieController {

    @Autowired
    private MovieServices movieServices;

    public MovieController(MovieServices movieService) {
        movieServices=movieService;
    }



   @GetMapping("/{id}")
    public Movie movieName(@PathVariable int id){
        return movieServices.movieName(id);
    }

    @GetMapping("/list")
    public List<Movie > moviesName(){
        return movieServices.moviesName();
    }

    @PostMapping("/")
    public Movie postMovie(@RequestBody Movie movie){
        return movieServices.save(movie);
    }

    @PutMapping("/movies")
    public Movie putMapping(@RequestBody Movie movie){


            return movieServices.save(movie);

    }
    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable int id){
        movieServices.delete(id);
        return "the deleted movie is : " + movieServices.movieName(id);

    }

}

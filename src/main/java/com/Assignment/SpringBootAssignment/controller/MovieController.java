package com.Assignment.SpringBootAssignment.controller;

import com.Assignment.SpringBootAssignment.VO.ResponceTemplate;
import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")

public class MovieController {

    private List<Movie> theMovies;

    @Autowired
    private MovieServices movieServices;

    public MovieController(MovieServices movieService) {
        movieServices=movieService;
    }

   @GetMapping("/{id}")
    public ResponceTemplate findMovieById(@PathVariable("id") int id){
        return movieServices.findMoviewithDetailsById(id);
    }

    @GetMapping("/")
    public List<Movie > findAllMovies(){
        return movieServices.findAllMovies();
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
        return "the deleted movie id : " + id;}
    @RequestMapping(value = "/paging/{pageNumber}/{pageSize}/{sortName}",method = RequestMethod.GET)
    public Page<Movie> moviepagination(@PathVariable int pageNumber, @PathVariable int pageSize,@PathVariable String sortName){
        return (Page<Movie>) movieServices.pagination(pageNumber,pageSize,sortName);
    }
}

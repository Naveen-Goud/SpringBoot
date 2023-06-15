package com.Assignment.SpringBootAssignment.controller;

import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.exceptions.MovieErrorResponse;
import com.Assignment.SpringBootAssignment.exceptions.MovieNotFoundException;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Movie findMovieById(@PathVariable int id){
//        if (id >= theMovies.size() || (id<0)){
//            throw new MovieNotFoundException("Movie id not found" + id);
//        }
        return movieServices.findMovieById(id);
    }


    @GetMapping
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
        return "the deleted movie is : " + movieServices.findMovieById(id);

    }
    @RequestMapping(value = "/paging/{pageNumber}/{pageSize}/{sortName}",method = RequestMethod.GET)
    public Page<Movie> movieagination(@PathVariable int pageNumber, @PathVariable int pageSize,@PathVariable String sortName){

        return (Page<Movie>) movieServices.pagination(pageNumber,pageSize,sortName);
    }

//    @ExceptionHandler
//    public ResponseEntity<MovieErrorResponse> handleException (MovieNotFoundException exc){
//        MovieErrorResponse error = new MovieErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<MovieErrorResponse> handleException(Exception exc){
//        MovieErrorResponse error = new MovieErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }
}

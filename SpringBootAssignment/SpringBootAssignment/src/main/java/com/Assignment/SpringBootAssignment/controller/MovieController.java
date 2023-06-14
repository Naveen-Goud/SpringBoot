package com.Assignment.SpringBootAssignment.controller;

import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    @RequestMapping("/list")
    public String listMovies(Model theModel) {

        List<Movie> theMovies =  movieServices.moviesName();
        theModel.addAttribute("movies", theMovies);

        return "list-movies";

    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Movie theMovie = new Movie();

        theModel.addAttribute("movies", theMovie);

        return "movie-form";
    }

    @GetMapping("/{id}")
    public Movie movieName(@PathVariable int id){
        return movieServices.movieName(id);
    }
//
//    @GetMapping("/list")
//    public List<Movie > moviesName(){
//        return movieServices.moviesName();
//    }

    @PostMapping("/saveMovies")
    public String saveMovies(@ModelAttribute("movies")   Movie movie){
         movieServices.save(movie);

         return "redirect:/movies/list";

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

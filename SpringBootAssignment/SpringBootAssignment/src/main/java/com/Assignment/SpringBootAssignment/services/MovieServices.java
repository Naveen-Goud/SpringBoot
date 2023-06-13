package com.Assignment.SpringBootAssignment.services;

import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Component
public class MovieServices implements MovieServicesInterface {

    @Autowired
    private  MovieRepository movieRepository;


    public MovieServices(MovieRepository movierepository){
      movieRepository=movierepository;
    }

    public Movie movieName(int id){
        return movieRepository.findByMovieId(id);
    }

    @Override
    public List<Movie> moviesName() {
        return movieRepository.findAll();
    }


    public Movie save(Movie themovie) {
        return  movieRepository.save(themovie);
    }

    public void delete(int id){
        movieRepository.deleteById(id);
            }
}

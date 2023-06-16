package com.Assignment.SpringBootAssignment.services;

import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Component
public class MovieServices implements MovieServicesInterface {

    @Autowired
    private  MovieRepository movieRepository;


    public MovieServices(MovieRepository movierepository){
      movieRepository=movierepository;
    }

    public Movie findMovieById(int id){
        return movieRepository.findByMovieId(id);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }


    public Movie save(Movie themovie) {
        return  movieRepository.save(themovie);
    }

    public void delete(int id){
        movieRepository.deleteById(id);
            }

    public Page<Movie> pagination(  int pageNumber, int pageSize, String sortName){
        Pageable pageable=null;
        if(null != sortName){
            pageable= PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,sortName);
        }else{
            pageable= PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,"movieName");

        }
        return movieRepository.findAll(pageable);
    }
}

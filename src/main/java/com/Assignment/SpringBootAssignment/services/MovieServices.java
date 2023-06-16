package com.Assignment.SpringBootAssignment.services;

import com.Assignment.SpringBootAssignment.VO.MovieDetails;
import com.Assignment.SpringBootAssignment.VO.ResponceTemplate;
import com.Assignment.SpringBootAssignment.entity.Movie;
import com.Assignment.SpringBootAssignment.exceptions.MovieNotFoundException;
import com.Assignment.SpringBootAssignment.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@Component
public class MovieServices implements MovieServicesInterface {

    @Autowired
    private  MovieRepository movieRepository;

    private RestTemplate restTemplate;


    public MovieServices(MovieRepository movierepository,RestTemplate therestTEmplate){

      this.movieRepository=movierepository;
      this.restTemplate=therestTEmplate;
    }
    public ResponceTemplate findMoviewithDetailsById(int id){
        ResponceTemplate vo = new ResponceTemplate();
        Movie movie= movieRepository.findByMovieId(id);

        if (movie == null) {
                 throw new MovieNotFoundException("Movie not found with ID: " + id);
        }
        MovieDetails movieDetails =
                restTemplate.getForObject(
                        "http://localhost:9002/MovieDetails/" + movie.getMovieId(),MovieDetails.class);
        vo.setMovie(movie);
        vo.setMovieDetails(movieDetails);
        return vo;
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

    public void setRestTemplate(RestTemplate restTemplate) {
    }
}

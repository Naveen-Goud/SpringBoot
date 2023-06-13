package com.Assignment.SpringBootAssignment.repository;

import com.Assignment.SpringBootAssignment.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieId(int id);
}

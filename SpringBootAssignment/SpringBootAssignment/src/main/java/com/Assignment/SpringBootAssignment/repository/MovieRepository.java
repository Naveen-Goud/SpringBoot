package com.Assignment.SpringBootAssignment.repository;

import com.Assignment.SpringBootAssignment.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieId(int id);

//    Page<Movie> paginationSort(Pageable pageable);
}

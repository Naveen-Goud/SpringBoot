package com.Assignment.SpringBootAssignment.VO;


import com.Assignment.SpringBootAssignment.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceTemplate {
    private Movie movie;
    private MovieDetails movieDetails;

    public ResponceTemplate(Movie movie) {
    }
}

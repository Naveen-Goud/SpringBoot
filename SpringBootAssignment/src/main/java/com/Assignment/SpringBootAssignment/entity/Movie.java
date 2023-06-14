package com.Assignment.SpringBootAssignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="movieid")
    private int movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "releaseDate")
    private String releaseDate;

    @Column(name = "rating")
    private String rating;


}

package com.Assignment.SpringBootAssignment.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {
    private int movieId;
    private  String heroName;
    private int collections;

    public MovieDetails(int id, String s) {
    }
}

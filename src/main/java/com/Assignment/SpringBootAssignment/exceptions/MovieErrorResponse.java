package com.Assignment.SpringBootAssignment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}

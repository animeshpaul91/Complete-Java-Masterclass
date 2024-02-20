package com.learnwiremock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {
    private String cast;
    private long movieId;
    private String name;
    private LocalDate releaseDate;
    private int year;
}

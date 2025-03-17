package com.movienetscape.appmanagementservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {

    private String movieId;
    private String title;
    private String genre;
    private boolean isPremium;
    private String storyHint;
    private String streamingUrl;
    private String imageUrl;
    private String thumbnail;
    private String duration;
    private int rating;
    private LocalDate releaseDate;
    private boolean isAdultMovie;


}


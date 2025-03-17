package com.movienetscape.appmanagementservice.model;

import com.movienetscape.appmanagementservice.dto.response.MovieResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseEntity {


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;


    @Column(nullable = false)
    private boolean isPremium;

    @Column(nullable = false)
    private String storyHint;

    @Column(nullable = false)
    private String streamingUrl;

    @Column
    private String imageUrl;

    @Column
    private String thumbnail;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private int rating = 0;

    @Column(nullable = false)
    private LocalDate releaseDate;

    private LocalDate uploadDate;

    private LocalDate lastUpdated;

    @Column(nullable = false)
    private boolean adultMovie;


    public MovieResponse mapToResponse() {
        return MovieResponse.builder()
                .movieId(getId())
                .genre(genre)
                .duration(duration)
                .streamingUrl(streamingUrl)
                .imageUrl(imageUrl)
                .title(title)
                .isPremium(isPremium)
                .rating(rating)
                .releaseDate(releaseDate)
                .storyHint(storyHint)
                .thumbnail(thumbnail)
                .genre(genre)
                .duration(duration)
                .isAdultMovie(adultMovie)
                .build();
    }

    @Override
    protected String generateId() {

        return "ntsc-movie-" + UUID.randomUUID().toString().substring(0, 7);
    }
}


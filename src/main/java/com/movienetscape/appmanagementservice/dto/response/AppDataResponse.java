package com.movienetscape.appmanagementservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppDataResponse {

    private long totalMovies;
    private int totalPages;
    private int currentPage;
    private List<MovieResponse> uploadedMovies;
    private List<PlanResponse> availablePlans;
    private List<AgeRatingResponse> ageRatings;
}

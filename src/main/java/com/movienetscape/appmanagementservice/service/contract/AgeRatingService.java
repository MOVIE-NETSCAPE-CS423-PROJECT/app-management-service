package com.movienetscape.appmanagementservice.service.contract;

import com.movienetscape.appmanagementservice.dto.request.AgeRatingRequest;
import com.movienetscape.appmanagementservice.dto.response.AgeRatingResponse;

import java.util.List;

public interface AgeRatingService {


    AgeRatingResponse createAgeRating(AgeRatingRequest ageRatingRequest);

    AgeRatingResponse updateAgeRating(String movieId, AgeRatingRequest ageRatingRequest);


    String deleteAgeRating(String planId);

    List<AgeRatingResponse> getAllAgeRatings();
}

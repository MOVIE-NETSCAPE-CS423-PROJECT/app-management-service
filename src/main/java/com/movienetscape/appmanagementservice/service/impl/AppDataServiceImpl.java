package com.movienetscape.appmanagementservice.service.impl;

import com.movienetscape.appmanagementservice.dto.response.AppDataResponse;
import com.movienetscape.appmanagementservice.dto.response.AgeRatingResponse;
import com.movienetscape.appmanagementservice.dto.response.MovieResponse;
import com.movienetscape.appmanagementservice.dto.response.PlanResponse;
import com.movienetscape.appmanagementservice.repository.AgeRatingRepository;
import com.movienetscape.appmanagementservice.repository.MovieRepository;
import com.movienetscape.appmanagementservice.repository.PlanRepository;
import com.movienetscape.appmanagementservice.service.contract.AppDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppDataServiceImpl implements AppDataService {

    private final MovieRepository movieRepository;
    private final PlanRepository planRepository;
    private final AgeRatingRepository ageRatingRepository;
    private final AgeRatingServiceImpl ageRatingServiceImpl;

    @Override
    public AppDataResponse getAppData(int page, int size) {

        Page<MovieResponse> moviePage = movieRepository.findAll(PageRequest.of(page, size))
                .map(movie -> MovieResponse.builder()
                        .movieId(movie.getId())
                        .title(movie.getTitle())
                        .genre(movie.getGenre())
                        .isAdultMovie(movie.isAdultMovie())
                        .streamingUrl(movie.getStreamingUrl())
                        .releaseDate(movie.getReleaseDate())
                        .duration(movie.getDuration())
                        .imageUrl(movie.getImageUrl())
                        .rating(movie.getRating())
                        .isPremium(movie.isPremium())
                        .storyHint(movie.getStoryHint())
                        .thumbnail(movie.getThumbnail())
                        .build());


        List<PlanResponse> planResponses = planRepository.findAll().stream()
                .map(plan -> PlanResponse.builder()
                        .planName(plan.getName())
                        .planId(plan.getId())
                        .price(plan.getPrice().doubleValue())
                        .benefits(plan.getBenefits())
                        .description(plan.getDescription())
                        .totalNumberOfMoviesAllowOnSubscription(plan.getTotalNumberOfMoviesAllowOnSubscription())
                        .totalNumberOfMoviesAllowOnNoSubscription(plan.getTotalNumberOfMoviesAllowOnNoSubscription())
                        .build())
                .toList();

        List<AgeRatingResponse> ageRatingResponse = ageRatingRepository.findAll().stream()
                .map(ageRating -> AgeRatingResponse.builder()
                        .categoryId(ageRating.getId())
                        .categoryName(ageRating.getCategoryName())
                        .build())
                .toList();

        return AppDataResponse.builder()
                .uploadedMovies(moviePage.getContent())
                .totalMovies(moviePage.getTotalElements())
                .totalPages(moviePage.getTotalPages())
                .currentPage(moviePage.getNumber())
                .availablePlans(planResponses)
                .ageRatings(ageRatingResponse)
                .build();
    }


}

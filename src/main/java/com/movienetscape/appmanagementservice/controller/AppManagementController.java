package com.movienetscape.appmanagementservice.controller;

import com.movienetscape.appmanagementservice.dto.request.AgeRatingRequest;
import com.movienetscape.appmanagementservice.dto.request.MovieRequest;
import com.movienetscape.appmanagementservice.dto.request.PlanRequest;
import com.movienetscape.appmanagementservice.dto.response.AppDataResponse;
import com.movienetscape.appmanagementservice.dto.response.AgeRatingResponse;
import com.movienetscape.appmanagementservice.dto.response.MovieResponse;
import com.movienetscape.appmanagementservice.dto.response.PlanResponse;
import com.movienetscape.appmanagementservice.service.contract.AppDataService;
import com.movienetscape.appmanagementservice.service.contract.AgeRatingService;
import com.movienetscape.appmanagementservice.service.contract.MovieService;
import com.movienetscape.appmanagementservice.service.contract.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/app-management")
@RequiredArgsConstructor
public class AppManagementController {

    private final MovieService movieService;
    private final PlanService planService;
    private final AgeRatingService ageRatingService;
    private final AppDataService appDataService;

    @GetMapping("/app-data")
    public ResponseEntity<AppDataResponse> getAppData(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(appDataService.getAppData(page, size));
    }


    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody @Valid MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(request));
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable String movieId, @RequestBody @Valid MovieRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovie(movieId, request));
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }


    @GetMapping("/movies/by-release-date/{releaseDate}")
    public ResponseEntity<List<MovieResponse>> getAllMoviesByReleaseDate(@PathVariable String releaseDate) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesByReleaseDate(releaseDate));
    }

    @GetMapping("/movies/by-category")
    public ResponseEntity<List<MovieResponse>> getAllMoviesByCategory(@RequestParam boolean isAdultMovie) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesByCategory(isAdultMovie));
    }

    @GetMapping("/movies/details/{movieId}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable String movieId) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovie(movieId));
    }

    @PostMapping("/plans")
    public ResponseEntity<PlanResponse> createPlan(@RequestBody @Valid PlanRequest planRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.createPlan(planRequest));
    }

    @PutMapping("/plans/{planId}")
    public ResponseEntity<PlanResponse> updatePlan(@PathVariable String planId, @RequestBody @Valid PlanRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.updatePlan(planId, request));
    }

    @DeleteMapping("/plans/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable String planId) {
        String message = planService.deletePlan(planId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans() {
        return ResponseEntity.status(HttpStatus.OK).body(planService.getAllPlans());
    }


    @PostMapping("/age-ratings")
    public ResponseEntity<AgeRatingResponse> createAgeRating(@RequestBody @Valid AgeRatingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ageRatingService.createAgeRating(request));
    }

    @PutMapping("/age-ratings/{ageRating}")
    public ResponseEntity<AgeRatingResponse> updateAgeRating(@PathVariable int ageRating, @RequestBody @Valid AgeRatingRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ageRatingService.updateAgeRating(String.valueOf(ageRating), request));
    }

    @DeleteMapping("/age-ratings/{ageRating}")
    public ResponseEntity<String> deleteAgeRating(@PathVariable String ageRating) {
        String message = ageRatingService.deleteAgeRating(ageRating);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/age-ratings/all")
    public ResponseEntity<List<AgeRatingResponse>> getAllAgeRatings() {
        return ResponseEntity.status(HttpStatus.OK).body(ageRatingService.getAllAgeRatings());
    }
}

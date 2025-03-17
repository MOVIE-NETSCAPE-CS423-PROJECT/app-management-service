package com.movienetscape.appmanagementservice.service.contract;

import com.movienetscape.appmanagementservice.dto.request.MovieRequest;
import com.movienetscape.appmanagementservice.dto.response.MovieResponse;
import com.movienetscape.appmanagementservice.model.Movie;
import com.movienetscape.appmanagementservice.util.MovieException;
import com.movienetscape.appmanagementservice.util.UtilFunctions;

import java.util.List;
import java.util.stream.Collectors;

public interface MovieService {


    MovieResponse createMovie(MovieRequest request);


    MovieResponse updateMovie(String movieId, MovieRequest request);

    void deleteMovie(String movieId);

    List<MovieResponse> getAllMovies();

    List<MovieResponse> getAllMoviesByReleaseDate(String releaseDate);

    List<MovieResponse> getAllMoviesByCategory(boolean isAdultMovie);

    MovieResponse getMovie(String movieId);
}

package com.movienetscape.appmanagementservice.service.impl;

import com.movienetscape.appmanagementservice.dto.request.MovieRequest;
import com.movienetscape.appmanagementservice.dto.response.MovieResponse;
import com.movienetscape.appmanagementservice.model.Movie;
import com.movienetscape.appmanagementservice.repository.MovieRepository;
import com.movienetscape.appmanagementservice.service.contract.MovieService;
import com.movienetscape.appmanagementservice.util.MovieException;
import com.movienetscape.appmanagementservice.util.RecordExistException;
import com.movienetscape.appmanagementservice.util.UtilFunctions;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieResponse createMovie(MovieRequest request) {

        return (MovieResponse) movieRepository.findMovieByTitle(request.getTitle())
                .map(movie -> {
                    throw new RecordExistException("Movie with title: " + movie.getTitle() + " already exists");
                })
                .orElseGet(() -> persist(new Movie(), request));
    }


    private MovieResponse persist(Movie movie, MovieRequest request) {
        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());
        movie.setReleaseDate(LocalDate.parse(request.getReleaseDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        movie.setPremium(request.getIsPremium());
        movie.setStoryHint(request.getStoryHint());
        movie.setStreamingUrl(request.getStreamUrl());
        movie.setImageUrl(request.getImageUrl());
        movie.setDuration(UtilFunctions.getDurationInTimeFormat(request.getDuration()));
        movie.setIsAdultMovie(request.getIsAdultMovie());
        movie.setLastUpdated(LocalDate.now());
        movie.setUploadDate(LocalDate.now());
        Movie savedMovie = movieRepository.save(movie);

        return savedMovie.mapToResponse();
    }

    public MovieResponse updateMovie(String movieId, MovieRequest request) {
        Movie movie = movieRepository.findMovieById(movieId)
                .orElseThrow(() -> new MovieException("Movie not found"));

        return persist(movie, request);
    }

    public void deleteMovie(String movieId) {
        Movie movie = movieRepository.findMovieById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movieRepository.delete(movie);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream().map(Movie::mapToResponse).collect(Collectors.toList());
    }

    public List<MovieResponse> getAllMoviesByReleaseDate(String releaseDate) {
        return movieRepository.findMovieByReleaseDate(UtilFunctions.toDate(releaseDate))
                .stream()
                .map(Movie::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<MovieResponse> getAllMoviesByCategory(boolean isAdultMovie) {
        return movieRepository.findMovieByIsAdultMovie(isAdultMovie).stream()
                .map(Movie::mapToResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse getMovie(String movieId) {
        Movie movie = movieRepository.findMovieById(movieId)
                .orElseThrow(() -> new MovieException("Movie not found"));
        return movie.mapToResponse();
    }


}

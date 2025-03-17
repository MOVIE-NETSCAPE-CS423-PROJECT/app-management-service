package com.movienetscape.appmanagementservice.repository;

import com.movienetscape.appmanagementservice.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    @NonNull
    Page<Movie> findAll(Pageable pageable);

    Optional<Movie> findMovieById(String id);

    Optional<Movie> findMovieByTitle(String title);

    List<Movie> findMovieByAdultMovie(boolean adultMovie);

    List<Movie> findMovieByReleaseDate(LocalDate releaseDate);


}


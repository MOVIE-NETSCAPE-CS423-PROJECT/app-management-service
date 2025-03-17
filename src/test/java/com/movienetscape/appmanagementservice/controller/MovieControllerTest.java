package com.movienetscape.appmanagementservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movienetscape.appmanagementservice.AppManagementServiceApplication;
import com.movienetscape.appmanagementservice.dto.request.MovieRequest;
import com.movienetscape.appmanagementservice.dto.response.MovieResponse;
import com.movienetscape.appmanagementservice.service.contract.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AppManagementController.class)
@ContextConfiguration(classes = AppManagementServiceApplication.class)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @MockitoBean
    private MovieService movieService;

    private MovieRequest request;

    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    void setUp() {

        request = MovieRequest.builder()
                .title("Inception")
                .genre("Sci-Fi")
                .isPremium(false)
                .streamUrl("http://stream.com/inception")
                .imageUrl("http://images.com/inception.jpg")
                .thumbnail("http://thumb.com/inception.jpg")
                .releaseDate(LocalDate.of(2010, 7, 16))
                .duration(148L).build();
    }


    @Test
    void testCreateMovie() throws Exception {
        // Mock response
        MovieResponse response = new MovieResponse();
        response.setTitle("Inception");
        response.setGenre("Sci-Fi");
        response.setReleaseDate(LocalDate.of(2010, 7, 16));
        response.setThumbnail("http://thumb.com/inception.jpg");
        response.setMovieId("");

        when(movieService.createMovie(any(MovieRequest.class)))
                .thenReturn(response);

        // Perform POST request
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated()) // Assert HTTP status
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.genre").value("Sci-Fi"));

        // Verify the interaction
        verify(movieService).createMovie(any(MovieRequest.class));

    }

    @Test
    void testUpdateMovie() {
    }

    @Test
    void testDeleteMovie() {
    }

    @Test
    void testGetAllMovies() {
    }

    @Test
    void testGetAllMoviesByReleaseDate() {
    }

    @Test
    void testAllGetAllMoviesByCategory() {
    }

    @Test
    void testGetMovie() {
    }
}
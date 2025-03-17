package com.movienetscape.appmanagementservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    @NotBlank(message = "Title can't be blank")
    private String title;

    @NotBlank(message = "Genre can't be blank")
    private String genre;

    @NotNull(message = "IsPremium can't be null")
    private Boolean isPremium;

    private String storyHint;

    @NotBlank(message = "Stream URL can't be blank")
    private String streamUrl;

    @NotBlank(message = "Image URL can't be blank")
    private String imageUrl;

    @NotBlank(message = "Thumbnail can't be blank")
    private String thumbnail;

    private Integer rating = 0;

    private boolean isAdultMovie;

    @NotNull(message = "Release date can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotNull(message = "Duration can't be null")
    private Long duration;
}

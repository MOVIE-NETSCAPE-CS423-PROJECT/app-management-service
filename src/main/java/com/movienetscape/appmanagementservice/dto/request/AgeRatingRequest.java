package com.movienetscape.appmanagementservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgeRatingRequest {

    @NotEmpty(message = "Category name must not be empty")
    @NotNull(message = "Category name must not be null")
    private String name;
}

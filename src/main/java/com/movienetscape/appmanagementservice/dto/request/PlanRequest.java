package com.movienetscape.appmanagementservice.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {


    @NotBlank(message = "Plan name must not be null or empty")
    private String name;


    @NotNull(message = "Benefits List must not be empty or null")
    private List<String> benefits;


    @NotNull(message = "Price must not be empty or null")
    private BigDecimal price;

    private String description;


    @NotNull(message = "MaxMoviesOnSubscription must not be null")
    private Integer maxMoviesOnSubscription;
    @NotNull(message = "MaxMoviesOnNoSubscription must not be null")
    private Integer maxMoviesOnNoSubscription;


}

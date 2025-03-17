package com.movienetscape.appmanagementservice.dto.request;


import jakarta.persistence.Column;
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

    @NotEmpty(message = "Plan name must not be empty")
    @NotNull(message = "Plan name must not be null")
    private String name;


    @NotNull(message = "Benefits List must not be empty or null")
    private List<String> benefits;


    @NotNull
    private BigDecimal price;

    private String description;


    @NotNull(message = "MaxMoviesOnSubscription must not be empty or null")
    private Integer maxMoviesOnSubscription;
    @NotNull(message = "MaxMoviesOnNoSubscription must not be empty or null")
    private Integer maxMoviesOnNoSubscription;


}

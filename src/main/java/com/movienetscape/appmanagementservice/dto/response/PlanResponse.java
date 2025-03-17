package com.movienetscape.appmanagementservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponse {

    private String planId;
    private String planName;
    private String description;
    private List<String> benefits;
    private Double price;
    private int totalNumberOfMoviesAllowOnSubscription;
    private int totalNumberOfMoviesAllowOnNoSubscription;
}

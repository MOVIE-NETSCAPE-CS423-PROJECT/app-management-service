package com.movienetscape.appmanagementservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgeRatingResponse {


    private String categoryId;

    private String categoryName;
}

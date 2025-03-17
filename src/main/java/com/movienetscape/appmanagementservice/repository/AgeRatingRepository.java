package com.movienetscape.appmanagementservice.repository;


import com.movienetscape.appmanagementservice.model.AgeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgeRatingRepository extends JpaRepository<AgeRating, String> {

    Optional<AgeRating> findCategoryByCategoryName(String categoryName);
}

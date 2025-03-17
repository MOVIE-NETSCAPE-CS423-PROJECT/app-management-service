package com.movienetscape.appmanagementservice.service.impl;


import com.movienetscape.appmanagementservice.dto.request.AgeRatingRequest;
import com.movienetscape.appmanagementservice.dto.response.AgeRatingResponse;
import com.movienetscape.appmanagementservice.model.AgeRating;
import com.movienetscape.appmanagementservice.repository.AgeRatingRepository;
import com.movienetscape.appmanagementservice.service.contract.AgeRatingService;
import com.movienetscape.appmanagementservice.util.RecordExistException;
import com.movienetscape.appmanagementservice.util.RecordNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgeRatingServiceImpl implements AgeRatingService {

    private final AgeRatingRepository ageRatingRepository;

    @Override
    public AgeRatingResponse createAgeRating(AgeRatingRequest ageRatingRequest) {

        ageRatingRepository.findCategoryByCategoryName(ageRatingRequest.getName())
                .ifPresent(ageRating -> {
                    throw new RecordExistException("Category with name " + ageRatingRequest.getName() + " already exists");
                });

        AgeRating ageRating = AgeRating.builder()
                .categoryName(ageRatingRequest.getName())
                .build();

        var savedAgeRating = ageRatingRepository.save(ageRating);


        return new AgeRatingResponse(savedAgeRating.getId(), savedAgeRating.getCategoryName());
    }


    @Override
    public AgeRatingResponse updateAgeRating(String ageRatingId, AgeRatingRequest ageRatingRequest) {

        AgeRating ageRating = ageRatingRepository.findById(ageRatingId)
                .orElseThrow(() -> new RecordNotFoundException("Category with ID " + ageRatingId + " not found"));

        if (ageRatingRequest.getName() != null && !ageRatingRequest.getName().equals(ageRating.getCategoryName())) {
            ageRating.setCategoryName(ageRatingRequest.getName());
        }
        var updatedCategory = ageRatingRepository.save(ageRating);
        return new AgeRatingResponse(updatedCategory.getId(), updatedCategory.getCategoryName());
    }


    @Override
    public String deleteAgeRating(String ageRatingId) {

        AgeRating ageRating = ageRatingRepository.findById(ageRatingId)
                .orElseThrow(() -> new RecordNotFoundException("Category with ID " + ageRatingId + " not found"));

        ageRatingRepository.delete(ageRating);
        return "Category with ID " + ageRatingId + " deleted successfully.";
    }


    @Override
    public List<AgeRatingResponse> getAllAgeRatings() {

        List<AgeRating> ageRatings = ageRatingRepository.findAll();
        return ageRatings.stream()
                .map(ageRating -> new AgeRatingResponse(ageRating.getId(),
                        ageRating.getCategoryName()))
                .toList();
    }
}


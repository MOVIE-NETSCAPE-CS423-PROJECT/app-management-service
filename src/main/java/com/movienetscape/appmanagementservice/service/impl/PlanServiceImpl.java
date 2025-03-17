package com.movienetscape.appmanagementservice.service.impl;


import com.movienetscape.appmanagementservice.dto.request.PlanRequest;
import com.movienetscape.appmanagementservice.dto.response.PlanResponse;
import com.movienetscape.appmanagementservice.model.Plan;
import com.movienetscape.appmanagementservice.repository.PlanRepository;
import com.movienetscape.appmanagementservice.service.contract.PlanService;
import com.movienetscape.appmanagementservice.util.RecordExistException;
import com.movienetscape.appmanagementservice.util.RecordNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public PlanResponse createPlan(PlanRequest planRequest) {

        planRepository.findPlanByName(planRequest.getName()).ifPresent(plan -> {
            throw new RecordExistException("Plan with name " + planRequest.getName() + " already exists");
        });

        Plan plan = Plan.builder()
                .price(planRequest.getPrice())
                .name(planRequest.getName())
                .benefits(planRequest.getBenefits())
                .description(planRequest.getDescription())
                .build();

        Plan savedPlan = planRepository.save(plan);


        return new PlanResponse(savedPlan.getId(), savedPlan.getName(),
                savedPlan.getDescription(),
                savedPlan.getBenefits(),
                savedPlan.getPrice().doubleValue(),
                savedPlan.getTotalNumberOfMoviesAllowOnSubscription(),
                savedPlan.getTotalNumberOfMoviesAllowOnNoSubscription());
    }

    @Override
    public PlanResponse updatePlan(String planId, PlanRequest request) {

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RecordNotFoundException("Plan with ID " + planId + " not found"));

        plan.setName(request.getName());
        plan.setDescription(request.getDescription());
        plan.setBenefits(request.getBenefits());
        plan.setPrice(request.getPrice());

        Plan updatedPlan = planRepository.save(plan);

        return new PlanResponse(updatedPlan.getId(),
                updatedPlan.getName(),
                updatedPlan.getDescription(),
                updatedPlan.getBenefits(),
                updatedPlan.getPrice().doubleValue(),
                updatedPlan.getTotalNumberOfMoviesAllowOnSubscription(),
                updatedPlan.getTotalNumberOfMoviesAllowOnNoSubscription()
        );
    }

    @Override
    public String deletePlan(String planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RecordNotFoundException("Plan with ID " + planId + " not found"));
        planRepository.delete(plan);
        return planId;
    }

    @Override
    public List<PlanResponse> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(plan -> new PlanResponse(plan.getId(),
                        plan.getName(),
                        plan.getDescription(),
                        plan.getBenefits(),
                        plan.getPrice().doubleValue(),
                        plan.getTotalNumberOfMoviesAllowOnSubscription(),
                        plan.getTotalNumberOfMoviesAllowOnNoSubscription()
                ))
                .collect(Collectors.toList());
    }
}

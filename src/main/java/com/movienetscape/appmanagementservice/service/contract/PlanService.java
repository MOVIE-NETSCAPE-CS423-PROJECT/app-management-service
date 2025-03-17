package com.movienetscape.appmanagementservice.service.contract;

import com.movienetscape.appmanagementservice.dto.request.PlanRequest;
import com.movienetscape.appmanagementservice.dto.response.PlanResponse;

import java.util.List;

public interface PlanService {

    PlanResponse createPlan(PlanRequest planRequest);

    PlanResponse updatePlan(String movieId, PlanRequest request);


    String deletePlan(String planId);


    List<PlanResponse> getAllPlans();
}


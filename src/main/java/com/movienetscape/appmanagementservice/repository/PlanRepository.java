package com.movienetscape.appmanagementservice.repository;


import com.movienetscape.appmanagementservice.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {

    Optional<Plan> findPlanByName(String name);
}

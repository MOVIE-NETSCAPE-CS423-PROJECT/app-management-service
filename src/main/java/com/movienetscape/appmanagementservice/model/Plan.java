package com.movienetscape.appmanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "plans")
public class Plan extends BaseEntity {


    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @ElementCollection
    @CollectionTable(name = "plan_benefits", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "benefit")
    private List<String> benefits;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int totalNumberOfMoviesAllowOnSubscription;

    @Column(nullable = false)
    private int totalNumberOfMoviesAllowOnNoSubscription;

    @Override
    protected String generateId() {
        return "ntsc-act-type-" + UUID.randomUUID().toString().substring(0, 7);
    }


}

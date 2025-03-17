package com.movienetscape.appmanagementservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "categories")
public class AgeRating extends BaseEntity {


    @Column(nullable = false)
    private String categoryName;


    @Override
    protected String generateId() {
        return "";
    }
}

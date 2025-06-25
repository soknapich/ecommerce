package com.domrey.ecommerce.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_view")
@org.hibernate.annotations.Immutable
public class StudentView {

    @Id
    private Long id;
    private String name;
    private String email;
    private Long country_id;
    private String country_title;
    @Column( name = "course_count")
    private Long course_count;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry_title() {
        return country_title;
    }
    public Long getCourse_count() {
        return course_count;
    }
}

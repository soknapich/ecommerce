package com.domrey.ecommerce.repository;

import com.domrey.ecommerce.entity.view.StudentView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSummaryRepository extends JpaRepository<StudentView, Long> {


}

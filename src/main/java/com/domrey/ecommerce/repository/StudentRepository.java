package com.domrey.ecommerce.repository;

import com.domrey.ecommerce.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

    @NativeQuery("SELECT * FROM student WHERE student.email LIKE %:domain")
    List<Student> findByDomain(@Param("domain") String domain);
}

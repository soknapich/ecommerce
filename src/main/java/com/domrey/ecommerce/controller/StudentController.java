package com.domrey.ecommerce.controller;

import com.domrey.ecommerce.entity.Student;
import com.domrey.ecommerce.entity.view.StudentView;
import com.domrey.ecommerce.repository.StudentRepository;
import com.domrey.ecommerce.repository.StudentSummaryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:8000")
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;
    private final StudentSummaryRepository summaryRepository;

    public StudentController(StudentRepository repository, StudentSummaryRepository summaryRepository) {

        this.repository = repository;
        this.summaryRepository = summaryRepository;
    }

    @PostMapping("/save")
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/findAllStudent")
    public List<Student> findAllStudent() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/findByEmail")
    public Student findStudent(@RequestParam("email") String email) {
        return repository.findByEmail(email);
    }

    @PatchMapping("/update")
    public Student findStudent(@RequestParam("student_id") Long id, @RequestParam("new_email") String email) {
        Optional<Student> studentOptional = repository.findById(id);
        if (studentOptional.isPresent()) {
            Student studentObj = studentOptional.get();
            studentObj.setEmail(email);
            return repository.save(studentObj);
        }
        return null;
    }

    //Find student by searching domain name
    @GetMapping("/findByEmailDomain")
    public List<Student> findByEmailDomain(@RequestParam("domain") String domain) {
        return repository.findByDomain(domain);
    }

    //Get all Student Summary
    @GetMapping("/findByStudentSummary")
    public List<StudentView> findByStudentSummary() {
        return summaryRepository.findAll();
    }

    @GetMapping("/findByStudentSummaryById")
    public Optional<StudentView> findByStudentSummaryById(@RequestParam("student_id") Long id) {
        return summaryRepository.findById(id);
    }

}

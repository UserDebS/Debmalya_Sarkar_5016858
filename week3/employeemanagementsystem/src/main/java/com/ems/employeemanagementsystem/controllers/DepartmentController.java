package com.ems.employeemanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.employeemanagementsystem.entities.Department;
import com.ems.employeemanagementsystem.repositories.DepartmentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository repository;

    @GetMapping
    public List<Department> getDepartments() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable long id) {
        return repository.findById(id).map(department -> {
            return ResponseEntity.ok().body(department);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Department> getDepartmentByEmployeeID(@PathVariable long id) {
        Department department = repository.findByEmployeeID(id);
        if(department != null) return ResponseEntity.ok().body(department);
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Department postDepartment(@RequestBody Department department) {      
        return repository.save(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> putDepartment(@PathVariable long id, @RequestBody Department newdepartment) {
        return repository.findById(id).map(department -> {
            department.setName(newdepartment.getName());
            return ResponseEntity.ok().body(repository.save(department));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable long id) {
        return repository.findById(id).map(department -> {
            repository.delete(department);
            return ResponseEntity.ok().body(department);
        }).orElse(ResponseEntity.notFound().build());
    }
}

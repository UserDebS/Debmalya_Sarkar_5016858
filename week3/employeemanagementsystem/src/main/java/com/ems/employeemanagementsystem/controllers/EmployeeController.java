package com.ems.employeemanagementsystem.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.employeemanagementsystem.entities.Employee;
import com.ems.employeemanagementsystem.projection.EmployeeProjection;
import com.ems.employeemanagementsystem.repositories.EmployeeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        return repository.findById(id).map(employee -> {
            return ResponseEntity.ok().body(employee);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{name}")
    public List<Employee> getByDepartmentName(@PathVariable String name) {
        return repository.findbyDepartmentName(name);
    }
    
    @GetMapping("/page/{pageno}")
    public Page<Employee> getEmployeesPaged(@PathVariable Integer pageno) {
        Pageable pageable = PageRequest.of(pageno, 10, Sort.by("name"));
        //Using both Page, Pageable, and sorting at the same time
        return repository.findAll(pageable);
    }

    @GetMapping("/contacts")
    public List<EmployeeProjection> getContacts() {
        return repository.findAllBy();
    }
    
    @PostMapping
    public Employee postEmployee(@RequestBody Employee employee) {      
        return repository.save(employee);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> putEmployee(@PathVariable long id, @RequestBody Employee newemployee) {
        return repository.findById(id).map(employee -> {
            employee.setName(newemployee.getName());
            employee.setEmail(newemployee.getEmail());
            employee.setDepartment(newemployee.getDepartment());
            return ResponseEntity.ok().body(repository.save(employee));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {
        return repository.findById(id).map(employee -> {
            repository.delete(employee);
            return ResponseEntity.ok().body(employee);
        }).orElse(ResponseEntity.notFound().build());
    }
}

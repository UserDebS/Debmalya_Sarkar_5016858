package com.ems.employeemanagementsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.employeemanagementsystem.entities.Employee;
import com.ems.employeemanagementsystem.projection.EmployeeProjection;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findbyDepartmentName(String departmentName);
    List<EmployeeProjection> findAllBy();
}

package com.ems.employeemanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ems.employeemanagementsystem.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d JOIN d.employees e where e.id = :employeeID")
    Department findByEmployeeID(@Param("employeeID") Long id);
}

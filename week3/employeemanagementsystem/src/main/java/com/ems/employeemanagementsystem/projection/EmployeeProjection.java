package com.ems.employeemanagementsystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    @Value("#{target.name + ' (' + target.email + ')'}")
    String getNameAndEmail();
}

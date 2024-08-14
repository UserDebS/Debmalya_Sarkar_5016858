package com.ems.employeemanagementsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("system_user");
    } 
}

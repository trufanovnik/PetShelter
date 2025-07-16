package com.ensep.logservice.repositories;

import com.ensep.logservice.entities.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}

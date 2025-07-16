package com.ensep.logservice.controller;

import com.ensep.logservice.entities.ErrorLog;
import com.ensep.logservice.repositories.ErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class ErrorLogController {

    private final ErrorLogRepository errorLogRepository;

    @GetMapping
    public List<ErrorLog> getLogs() {
        return errorLogRepository.findAll();
    }
}

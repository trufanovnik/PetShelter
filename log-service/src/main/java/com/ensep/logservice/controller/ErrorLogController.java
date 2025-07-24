package com.ensep.logservice.controller;

import com.ensep.logservice.entities.ErrorLog;
import com.ensep.logservice.mapper.ErrorLogMapper;
import com.ensep.logservice.repositories.ErrorLogRepository;
import com.ensep.shared.dto.ErrorLogDTO;
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
    private final ErrorLogMapper errorLogMapper;

    @GetMapping
    public List<ErrorLogDTO> getLogs() {
        return errorLogMapper.toDtoList(errorLogRepository.findAll());
    }
}

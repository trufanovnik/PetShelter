package com.ensep.petshelter.controllers.rest.logs;

import com.ensep.petshelter.services.feign.LogServiceClient;
import com.ensep.shared.dto.ErrorLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogClientController {

    private final LogServiceClient logServiceClient;

    @GetMapping
    public List<ErrorLogDTO> getLogs() {
        return logServiceClient.getLogs();
    }
}

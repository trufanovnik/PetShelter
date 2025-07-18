package com.ensep.petshelter.services.feign;

import com.ensep.shared.dto.ErrorLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "log-service-client", url = "${log-service.url}")
public interface LogServiceClient {

    @GetMapping("/api/logs")
    List<ErrorLogDTO> getLogs();
}

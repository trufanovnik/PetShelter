package com.ensep.logservice.services;

import com.ensep.logservice.entities.ErrorLog;
import com.ensep.logservice.repositories.ErrorLogRepository;
import com.ensep.shared.dto.ErrorLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorLogConsumerService {

    private final ErrorLogRepository errorLogRepository;

    @KafkaListener(topics = "logs-topic", groupId = "log-service-group")
    public void listen(ErrorLogDTO errorLogDto) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setMessage(errorLogDto.getMessage());
        errorLog.setTimestamp(errorLogDto.getTimestamp());
        errorLogRepository.save(errorLog);
    }
}

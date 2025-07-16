package com.ensep.petshelter.services.kafka;

import com.ensep.shared.dto.ErrorLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorLogProducerService {

    private final KafkaTemplate<String, ErrorLogDTO> kafkaTemplate;

    public void sendErrorWithTimeout(ErrorLogDTO errorLog) {
        kafkaTemplate.send("logs-topic", errorLog);
    }
}

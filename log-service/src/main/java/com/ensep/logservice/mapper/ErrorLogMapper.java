package com.ensep.logservice.mapper;

import com.ensep.logservice.entities.ErrorLog;
import com.ensep.shared.dto.ErrorLogDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ErrorLogMapper {

    public ErrorLogDTO toDto(ErrorLog entity) {
        ErrorLogDTO errorLogDTO = new ErrorLogDTO();
        errorLogDTO.setMessage(entity.getMessage());
        errorLogDTO.setTimestamp(entity.getTimestamp());
        return errorLogDTO;
    }

    public List<ErrorLogDTO> toDtoList(List<ErrorLog> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

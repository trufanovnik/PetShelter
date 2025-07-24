package com.ensep.petshelter.exceptions;

import com.ensep.petshelter.services.kafka.ErrorLogProducerService;
import com.ensep.shared.dto.ErrorLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorLogProducerService errorLogProducerService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
        createAndSendErrorLog(ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(AccountExistException.class)
    public ResponseEntity<String> handleAccountExist(AccountExistException ex) {
        createAndSendErrorLog(ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleAllException(Exception ex) {
//        createAndSendErrorLog(ex);
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Произошла непредвиденная ошибка.");
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        createAndSendErrorLog(new AccountExistException("Пользователь с таким username уже существует"));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Пользователь с таким username уже существует");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        createAndSendErrorLog(ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("У вас нет прав доступа");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredential(BadCredentialsException ex) {
        createAndSendErrorLog(ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Логин или пароль не совпадают");
    }

    public void createAndSendErrorLog(Exception ex) {
        ErrorLogDTO errorLogDTO = new ErrorLogDTO();
        errorLogDTO.setMessage(ex.getClass().getSimpleName());
        errorLogDTO.setTimestamp(LocalDateTime.now());
        new Thread(() -> errorLogProducerService.sendErrorWithTimeout(errorLogDTO)).start();
    }
}

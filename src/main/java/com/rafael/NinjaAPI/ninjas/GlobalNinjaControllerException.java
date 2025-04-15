package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.ninjas.exceptions.EmailAlreadyExistsException;
import com.rafael.NinjaAPI.ninjas.exceptions.NinjaNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalNinjaControllerException {

    @ExceptionHandler(NinjaNotFoundExecption.class)
    public ResponseEntity<?> handlerNinjaNotFoundException(NinjaNotFoundExecption e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handlerNinjaNotFoundException(EmailAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception e){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        body.put("error", e.getMessage());
        body.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

}

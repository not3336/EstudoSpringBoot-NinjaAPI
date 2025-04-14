package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.ninjas.exceptions.NinjaNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalNinjaControllerException {

    @ExceptionHandler(NinjaNotFoundExecption.class)
    public ResponseEntity<?> handlerNinjaNotFoundException(NinjaNotFoundExecption e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}

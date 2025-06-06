package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.missions.exceptions.MissionNotFoundException;
import com.rafael.NinjaAPI.missions.exceptions.MissionRankNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalMissionControllerException {

    @ExceptionHandler(MissionNotFoundException.class)
    public ResponseEntity<?> handlerMissionNotFoundException(MissionNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MissionRankNotFoundException.class)
    public ResponseEntity<?> handlerMissionRankNotFoundException(MissionRankNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

package com.rafael.NinjaAPI.missions.exceptions;

public class MissionNotFoundException extends RuntimeException{
    public MissionNotFoundException(String message) {
        super(message);
    }
}

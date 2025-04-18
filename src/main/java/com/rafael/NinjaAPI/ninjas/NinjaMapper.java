package com.rafael.NinjaAPI.ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaMapper() {
    }

    public NinjaModel map(NinjaDto ninjaDto){
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDto.getId());
        ninjaModel.setName(ninjaDto.getName());
        ninjaModel.setEmail(ninjaDto.getEmail());
        ninjaModel.setAge(ninjaDto.getAge());
        ninjaModel.setRank(ninjaDto.getRank());
        ninjaModel.setImgUrl(ninjaDto.getImgUrl());
        ninjaModel.setMission(ninjaDto.getMission());
        return ninjaModel;
    }
    public NinjaDto map(NinjaModel ninjaModel){
        NinjaDto ninjaDto = new NinjaDto();
        ninjaDto.setId(ninjaModel.getId());
        ninjaDto.setName(ninjaModel.getName());
        ninjaDto.setEmail(ninjaModel.getEmail());
        ninjaDto.setAge(ninjaModel.getAge());
        ninjaDto.setRank(ninjaModel.getRank());
        ninjaDto.setImgUrl(ninjaModel.getImgUrl());
        ninjaDto.setMission(ninjaModel.getMission());
        return ninjaDto;
    }
}

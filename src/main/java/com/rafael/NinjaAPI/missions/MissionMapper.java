package com.rafael.NinjaAPI.missions;

import org.springframework.stereotype.Component;

@Component
public class MissionMapper {
    public MissionModel map(MissionDto missionDto){
        MissionModel missionModel = new MissionModel();
        missionModel.setId(missionDto.getId());
        missionModel.setName(missionDto.getName());
        missionModel.setDescription(missionDto.getDescription());
        missionModel.setRank(missionDto.getRank());
        return missionModel;
    }

    public MissionDto map(MissionModel missionModel){
        MissionDto missionDto = new MissionDto();
        missionDto.setId(missionModel.getId());
        missionDto.setName(missionModel.getName());
        missionDto.setDescription(missionModel.getDescription());
        missionDto.setRank(missionModel.getRank());
        return missionDto;
    }
}

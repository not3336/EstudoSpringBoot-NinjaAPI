package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.missions.exceptions.MissionNotFoundException;
import com.rafael.NinjaAPI.missions.exceptions.MissionRankNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

    public List<MissionModel> findAll(){
        return missionRepository.findAll();
    }

    public MissionModel findById(Long id) throws MissionNotFoundException{
        return missionRepository.findById(id)
                .orElseThrow(() -> new MissionNotFoundException("Mission with ID " + id + " not found"));
    }

    public MissionModel save(MissionDto missionDto) throws MissionRankNotFoundException{
        MissionModel mission = new MissionModel();
        mission.setName(missionDto.name());
        mission.setDescription(missionDto.description());
        try{
            mission.setRank(MissionRank.valueOf(missionDto.rank()));
        }catch (IllegalArgumentException e){
            throw new MissionRankNotFoundException("Rank with name " + missionDto.rank() + " not found");
        }
        return missionRepository.save(mission);
    }

    public MissionModel update(Long id, MissionDto updateMissionDto) throws MissionNotFoundException, MissionRankNotFoundException{
        MissionModel mission = missionRepository.findById(id)
                .orElseThrow(()-> new MissionNotFoundException("Mission with ID " + id + " not found"));

        mission.setName(updateMissionDto.name());
        mission.setDescription(updateMissionDto.description());
        try{
            mission.setRank(MissionRank.valueOf(String.valueOf(updateMissionDto.rank())));
        }catch (IllegalArgumentException e){
            throw new MissionRankNotFoundException("Rank with name " + updateMissionDto.rank() + " not found");
        }
        return  missionRepository.save(mission);

    }

    public void deleteById(Long id) throws MissionNotFoundException{
        MissionModel mission = missionRepository.findById(id)
                .orElseThrow(()->new MissionNotFoundException("Mission with ID " + id + " not found"));
        missionRepository.delete(mission);
    }
}

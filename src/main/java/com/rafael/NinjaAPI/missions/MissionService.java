package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.missions.exceptions.MissionNotFoundException;
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
        var exists = missionRepository.findById(id);
        if (exists.isEmpty()){
            throw new MissionNotFoundException("Mission with ID " + id + " not found");
        }
        return exists.get();
    }

    public MissionModel save(MissionModel mission){
        return missionRepository.save(mission);
    }

    public MissionModel update(Long id, MissionModel updateMission) throws MissionNotFoundException{
        var exists = missionRepository.findById(id);
        if (exists.isEmpty()){
            throw new MissionNotFoundException("Mission with ID " + id + " not found");
        }
        MissionModel mission = exists.get();
        mission.setName(updateMission.getName());
        mission.setDifficulty(updateMission.getDifficulty());

        return  missionRepository.save(mission);

    }

    public void deleteById(Long id) throws MissionNotFoundException{
        var exists = missionRepository.findById(id);
        if (exists.isEmpty()){
            throw new MissionNotFoundException("Mission with ID " + id + " not found");
        }
        missionRepository.delete(exists.get());
    }
}

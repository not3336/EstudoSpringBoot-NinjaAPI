package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.missions.exceptions.MissionNotFoundException;
import com.rafael.NinjaAPI.missions.exceptions.MissionRankNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;

    public MissionService(MissionRepository missionRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
    }

    public List<MissionDto> findAll(){
        return missionRepository.findAll().stream().map(missionMapper::map).toList();
    }

    public MissionDto findById(Long id) throws MissionNotFoundException{
        return missionMapper.map(
                missionRepository.findById(id)
                        .orElseThrow(() -> new MissionNotFoundException("Mission with ID " + id + " not found"))
        );
    }

    public MissionDto save(MissionDto missionDto) throws MissionRankNotFoundException{
        return missionMapper.map(missionRepository.save(missionMapper.map(missionDto)));
    }

    public MissionDto update(Long id, MissionDto updateMissionDto) throws MissionNotFoundException, MissionRankNotFoundException{
        missionRepository.findById(id)
                .orElseThrow(()-> new MissionNotFoundException("Mission with ID " + id + " not found"));
        MissionModel mission = missionMapper.map(updateMissionDto);
        mission.setId(id);
        return missionMapper.map(missionRepository.save(mission));

    }

    public void deleteById(Long id) throws MissionNotFoundException{
        MissionModel mission = missionRepository.findById(id)
                .orElseThrow(()->new MissionNotFoundException("Mission with ID " + id + " not found"));
        missionRepository.delete(mission);
    }
}

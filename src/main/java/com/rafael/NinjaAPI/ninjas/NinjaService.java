package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.missions.MissionModel;
import com.rafael.NinjaAPI.missions.MissionService;
import com.rafael.NinjaAPI.missions.exceptions.MissionNotFoundException;
import com.rafael.NinjaAPI.ninjas.exceptions.EmailAlreadyExistsException;
import com.rafael.NinjaAPI.ninjas.exceptions.NinjaNotFoundExecption;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final MissionService missionService;

    public NinjaService(NinjaRepository ninjaRepository, MissionService missionService) {
        this.ninjaRepository = ninjaRepository;
        this.missionService = missionService;
    }

    public List<NinjaModel> findAll(){
        return ninjaRepository.findAll();
    }

    public NinjaModel save(NinjaDto ninjaDto) throws MissionNotFoundException, EmailAlreadyExistsException {
        var exists = ninjaRepository.findByEmail(ninjaDto.getEmail());
        if (exists.isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }
        MissionModel mission = missionService.findById(ninjaDto.getMissionId());
        NinjaModel ninja = new NinjaModel();
        ninja.setName(ninjaDto.getName());
        ninja.setEmail(ninjaDto.getEmail());
        ninja.setImgUrl(ninjaDto.getImgUrl());
        ninja.setAge(ninjaDto.getAge());
        ninja.setRank(ninjaDto.getRank());
        ninja.setMission(mission);
        return ninjaRepository.save(ninja);
    }

    public NinjaModel findById(Long id) throws NinjaNotFoundExecption{
        var exists = ninjaRepository.findById(id);
        if (exists.isEmpty()){
            throw new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found");
        }
        return exists.get();
    }

    public NinjaModel update(Long id, NinjaDto updateNinja) throws NinjaNotFoundExecption, MissionNotFoundException{
        var exists = ninjaRepository.findById(id);
        if (exists.isEmpty()){
            throw new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found");
        }
        MissionModel mission = missionService.findById(updateNinja.getMissionId());
        NinjaModel ninja = exists.get();
        if (!updateNinja.getEmail().equals(ninja.getEmail()) && ninjaRepository.findByEmail(updateNinja.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }

        ninja.setName(updateNinja.getName());
        ninja.setEmail(updateNinja.getEmail());
        ninja.setImgUrl(updateNinja.getImgUrl());
        ninja.setAge(updateNinja.getAge());
        ninja.setRank(updateNinja.getRank());
        ninja.setMission(mission);
        return ninjaRepository.save(ninja);
    }

    public void delete(Long id){
        var exists = ninjaRepository.findById(id);
        if (exists.isEmpty()){
            throw new NinjaNotFoundExecption("Ninja with ID " + id + " not found");
        }
        ninjaRepository.delete(exists.get());
    }
}

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
        var exists = ninjaRepository.findByEmail(ninjaDto.email());
        if (exists.isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }
        MissionModel mission = missionService.findById(ninjaDto.missionId());
        NinjaModel ninja = new NinjaModel();
        ninja.setName(ninjaDto.name());
        ninja.setEmail(ninjaDto.email());
        ninja.setImgUrl(ninjaDto.imgUrl());
        ninja.setAge(ninjaDto.age());
        ninja.setRank(ninjaDto.rank());
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
        MissionModel mission = missionService.findById(updateNinja.missionId());
        NinjaModel ninja = exists.get();
        if (!updateNinja.email().equals(ninja.getEmail()) && ninjaRepository.findByEmail(updateNinja.email()).isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }

        ninja.setName(updateNinja.name());
        ninja.setEmail(updateNinja.email());
        ninja.setImgUrl(updateNinja.imgUrl());
        ninja.setAge(updateNinja.age());
        ninja.setRank(updateNinja.rank());
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

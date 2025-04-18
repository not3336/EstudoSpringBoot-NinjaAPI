package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.missions.MissionModel;
import com.rafael.NinjaAPI.missions.MissionService;
import com.rafael.NinjaAPI.missions.exceptions.MissionNotFoundException;
import com.rafael.NinjaAPI.ninjas.exceptions.EmailAlreadyExistsException;
import com.rafael.NinjaAPI.ninjas.exceptions.NinjaNotFoundExecption;
import com.rafael.NinjaAPI.ninjas.exceptions.NinjaRankNotFoundException;
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

    public NinjaModel save(NinjaDto ninjaDto) throws MissionNotFoundException, EmailAlreadyExistsException, NinjaRankNotFoundException {
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
        try{
            ninja.setRank(NinjaRank.valueOf(ninjaDto.rank().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new NinjaRankNotFoundException("Rank " + ninjaDto.rank() + " not found");
        }
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

    public NinjaModel update(Long id, NinjaDto updateNinjaDto)
            throws NinjaNotFoundExecption, MissionNotFoundException, EmailAlreadyExistsException{
        NinjaModel ninja = ninjaRepository.findById(id)
                .orElseThrow(()->new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found"));
        MissionModel mission = missionService.findById(updateNinjaDto.missionId());

        if (!updateNinjaDto.email().equals(ninja.getEmail()) && ninjaRepository.findByEmail(updateNinjaDto.email()).isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }

        ninja.setName(updateNinjaDto.name());
        ninja.setEmail(updateNinjaDto.email());
        ninja.setImgUrl(updateNinjaDto.imgUrl());
        ninja.setAge(updateNinjaDto.age());
        try{
            ninja.setRank(NinjaRank.valueOf(updateNinjaDto.rank().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new NinjaRankNotFoundException("Rank " + updateNinjaDto.rank() + " not found");
        }
        ninja.setMission(mission);
        return ninjaRepository.save(ninja);
    }

    public void delete(Long id){
        NinjaModel ninja = ninjaRepository.findById(id)
                .orElseThrow(()-> new NinjaNotFoundExecption("Ninja with ID " + id + " not found"));
        ninjaRepository.delete(ninja);
    }
}

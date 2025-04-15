package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.missions.MissionModel;
import com.rafael.NinjaAPI.missions.MissionRepository;
import com.rafael.NinjaAPI.missions.MissionService;
import com.rafael.NinjaAPI.ninjas.exceptions.EmailAlreadyExistsException;
import com.rafael.NinjaAPI.ninjas.exceptions.NinjaNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public NinjaModel findById(Long id) throws NinjaNotFoundExecption{
        var exists = ninjaRepository.findById(id);
        if (exists.isEmpty()){
            throw new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found");
        }
        return exists.get();
    }

    public NinjaModel save(Map<String, String> request){
        MissionModel mission = missionService.findById(Long.parseLong(request.get("mission_id")));
        NinjaModel ninja = new NinjaModel(
                null,
                request.get("name"),
                request.get("email"),
                request.get("img_url"),
                Integer.parseInt(request.get("age")),
                request.get("rank"),
                mission);
        return ninjaRepository.save(ninja);
    }

    public NinjaModel update(Long id, Map<String, String> request){
        var exists = ninjaRepository.findById(id);
        if (exists.isEmpty()){
            throw new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found");
        }
        if (ninjaRepository.findByEmail(request.get("email")).isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }
        NinjaModel ninja = exists.get();
        ninja.setName(request.get("name"));
        ninja.setEmail(request.get("email"));
        ninja.setAge(Integer.parseInt(request.get("age")));
        ninja.setRank(request.get("rank"));
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

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
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDto> findAll(){
        return ninjaRepository.findAll().stream().map(ninjaMapper::map).toList();
    }

    public NinjaDto save(NinjaDto ninjaDto) throws MissionNotFoundException, EmailAlreadyExistsException, NinjaRankNotFoundException {
        var exists = ninjaRepository.findByEmail(ninjaDto.getEmail());
        if (exists.isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }
        return ninjaMapper.map(ninjaRepository.save(ninjaMapper.map(ninjaDto)));
    }

    public NinjaDto findById(Long id) throws NinjaNotFoundExecption{
        NinjaModel ninja = ninjaRepository.findById(id)
                .orElseThrow(() -> new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found"));
        return ninjaMapper.map(ninja);
    }

    public NinjaDto update(Long id, NinjaDto updateNinjaDto)
            throws NinjaNotFoundExecption, MissionNotFoundException, EmailAlreadyExistsException{
        NinjaModel ninja = ninjaRepository.findById(id)
                .orElseThrow(()->new NinjaNotFoundExecption("Ninja with ID " + id + " Not Found"));

        if (!updateNinjaDto.getEmail().equals(ninja.getEmail()) && ninjaRepository.findByEmail(updateNinjaDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("there is already a ninja with this email");
        }


        return ninjaMapper.map(ninjaRepository.save(ninjaMapper.map(updateNinjaDto)));
    }

    public void delete(Long id){
        NinjaModel ninja = ninjaRepository.findById(id)
                .orElseThrow(()-> new NinjaNotFoundExecption("Ninja with ID " + id + " not found"));
        ninjaRepository.delete(ninja);
    }
}

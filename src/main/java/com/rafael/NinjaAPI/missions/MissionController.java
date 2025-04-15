package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.ninjas.NinjaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public ResponseEntity<List<MissionModel>> findAll(){
        return ResponseEntity.ok(missionService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MissionModel> findById(@PathVariable Long id){
        return ResponseEntity.ok(missionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MissionModel> register(@RequestBody MissionModel mission){
        return ResponseEntity.ok(missionService.save(mission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionModel> update(@PathVariable Long id, @RequestBody MissionModel updateMission){
        return ResponseEntity.ok(missionService.update(id, updateMission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        missionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

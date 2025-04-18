package com.rafael.NinjaAPI.missions;

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
    public ResponseEntity<List<MissionDto>> findAll(){
        return ResponseEntity.ok(missionService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MissionDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(missionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MissionDto> register(@RequestBody MissionDto missionDto){
        return ResponseEntity.ok(missionService.save(missionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionDto> update(@PathVariable Long id, @RequestBody MissionDto updateMissionDto){
        return ResponseEntity.ok(missionService.update(id, updateMissionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        missionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.rafael.NinjaAPI.ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {
    @Autowired
    private NinjaService ninjaService;

    @GetMapping()
    public ResponseEntity<List<NinjaDto>> findALl(){
        return ResponseEntity.ok(ninjaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NinjaDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(ninjaService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody NinjaDto ninjaDto){
        NinjaDto ninja = ninjaService.save(ninjaDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully created ninja: \nName: " + ninja.getName() + "\nID: " + ninja.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NinjaDto> update(@PathVariable Long id, @RequestBody NinjaDto updateNinja){
        return ResponseEntity.ok(ninjaService.update(id, updateNinja));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        ninjaService.delete(id);
        return ResponseEntity.ok("Ninja with ID " + id + " deleted successfully");
    }
}

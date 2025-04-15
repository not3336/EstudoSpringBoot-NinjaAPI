package com.rafael.NinjaAPI.ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {
    @Autowired
    private NinjaService ninjaService;

    @GetMapping
    public ResponseEntity<List<NinjaModel>> findALl(){
        return ResponseEntity.ok(ninjaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NinjaModel> findById(@PathVariable Long id){
        return ResponseEntity.ok(ninjaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<NinjaModel> register(@RequestBody Map<String, String> request){
        return ResponseEntity.ok(ninjaService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NinjaModel> update(@PathVariable Long id, @RequestBody Map<String, String> request){
        return ResponseEntity.ok(ninjaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ninjaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

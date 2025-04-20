package com.rafael.NinjaAPI.ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "List all ninja", description = "Returns a list of all ninjas registered in the database")
    public ResponseEntity<List<NinjaDto>> findALl(){
        return ResponseEntity.ok(ninjaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a ninja", description = "Returns a ninja with a specific ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja successfully found"),
            @ApiResponse(responseCode = "404", description = "Ninja not found")
    })
    public ResponseEntity<NinjaDto> findById(
            @Parameter(description = "The user sends the id in the request path")
            @PathVariable Long id)
    {
        return ResponseEntity.ok(ninjaService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "register a new ninja", description = "Receives the data and registers a new ninja in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja created successfully"),
            @ApiResponse(responseCode = "400", description = "Error creating ninja")
    })
    public ResponseEntity<String> register(@RequestBody NinjaDto ninjaDto)
    {
        NinjaDto ninja = ninjaService.save(ninjaDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully created ninja: \nName: " + ninja.getName() + "\nID: " + ninja.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change ninja by id", description = "Change ninja data by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja successfully changed"),
            @ApiResponse(responseCode = "404", description = "Ninja not found")
    })
    public ResponseEntity<NinjaDto> update(
            @Parameter(description = "The user sends the id in the request path")
            @PathVariable Long id,
            @RequestBody NinjaDto updateNinja)
    {
        return ResponseEntity.ok(ninjaService.update(id, updateNinja));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ninja by id", description = "Delete specific ID ninja from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ninja successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Ninja not found")
    })
    public ResponseEntity<String> delete(
            @Parameter(description = "The user sends the id in the request path")
            @PathVariable Long id)
    {
        ninjaService.delete(id);
        return ResponseEntity.ok("Ninja with ID " + id + " deleted successfully");
    }
}

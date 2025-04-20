package com.rafael.NinjaAPI.missions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "list all missions", description = "Returns a list of all missions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions successfully found"),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<List<MissionDto>> findAll()
    {
        return ResponseEntity.ok(missionService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "returns a mission by id", description = "Returns the mission with the specified Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission successfully found"),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<MissionDto> findById(
            @Parameter(description = "The user sends the id in the request path")
            @PathVariable Long id)
    {
        return ResponseEntity.ok(missionService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Register a mission", description = "Registers a mission in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission registered successfully"),
            @ApiResponse(responseCode = "400", description = "Error registered mission")
    })
    public ResponseEntity<MissionDto> register(@RequestBody MissionDto missionDto)
    {
        return ResponseEntity.ok(missionService.save(missionDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change a mission", description = "Registers a mission with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission changed successfully"),
            @ApiResponse(responseCode = "400", description = "Error changed mission")
    })
    public ResponseEntity<MissionDto> update(
            @Parameter(description = "The user sends the id in the request path")
            @PathVariable Long id,
            @RequestBody MissionDto updateMissionDto)
    {
        return ResponseEntity.ok(missionService.update(id, updateMissionDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a mission", description = "Deleted a mission with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "The user sends the id in the request path")
            @PathVariable Long id)
    {
        missionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

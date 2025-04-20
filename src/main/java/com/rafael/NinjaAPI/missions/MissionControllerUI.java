package com.rafael.NinjaAPI.missions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/missions/ui")
public class MissionControllerUI {

    private final MissionService missionService;

    public MissionControllerUI(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public String findAll(Model model){
        List<MissionDto> missions = missionService.findAll();
        model.addAttribute("missions", missions);
        return "listMissions";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        MissionDto mission = missionService.findById(id);
        model.addAttribute("mission", mission);
        return "missionDetails";
    }

    @GetMapping("/register")
    public String formRegister(Model model){
        model.addAttribute("mission", new MissionDto());
        return "formMission";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute MissionDto mission){
        missionService.save(mission);
        return "redirect:/missions/ui";
    }

    @GetMapping("/update/{id}")
    public String formUpdate(@PathVariable Long id, Model model){
        MissionDto mission = missionService.findById(id);
        model.addAttribute("mission", mission);
        return "formUpdateMission";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute MissionDto mission){
        missionService.update(id, mission);
        return "redirect:/missions/ui";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        missionService.deleteById(id);
        return "redirect:/missions/ui";
    }

}

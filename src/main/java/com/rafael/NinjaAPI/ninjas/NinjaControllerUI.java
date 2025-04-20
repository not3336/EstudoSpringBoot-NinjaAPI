package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.missions.MissionDto;
import com.rafael.NinjaAPI.missions.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissionService missionService;

    public NinjaControllerUI(NinjaService ninjaService, MissionService missionService) {
        this.ninjaService = ninjaService;
        this.missionService = missionService;
    }

    @GetMapping
    public String findAll(Model model){
        List<NinjaDto> ninjas = ninjaService.findAll();
        model.addAttribute("ninjas", ninjas);
        return "listNinjas";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        NinjaDto ninja = ninjaService.findById(id);
        model.addAttribute("ninja", ninja);
        return "ninjaDetails";
    }

    @GetMapping("/register")
    public String formRegister(Model model){
        List<MissionDto> missions = missionService.findAll();
        model.addAttribute("ninja", new NinjaDto());
        model.addAttribute("missions", missions);
        return "formNinja";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute NinjaDto ninja){
        ninjaService.save(ninja);
        return "redirect:/ninjas/ui";
    }

    @GetMapping("/update/{id}")
    public String formUpdate(@PathVariable Long id, Model model){
        List<MissionDto> missions = missionService.findAll();
        NinjaDto ninja = ninjaService.findById(id);
        model.addAttribute("ninja", ninja);
        model.addAttribute("missions", missions);
        return "formUpdateNinja";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute NinjaDto ninja){
        ninjaService.update(id, ninja);
        return "redirect:/ninjas/ui";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        ninjaService.delete(id);
        return "redirect:/ninjas/ui";
    }

}

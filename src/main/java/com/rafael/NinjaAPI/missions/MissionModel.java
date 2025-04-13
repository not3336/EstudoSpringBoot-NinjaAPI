package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_mission")
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String difficulty;

    //@OneToMany Uma missão pode ter varios ninjas
    @OneToMany(mappedBy = "mission")
    private List<NinjaModel> ninjas;

    public MissionModel() {
    }

    public MissionModel(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}

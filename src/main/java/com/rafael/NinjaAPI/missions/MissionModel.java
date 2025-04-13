package com.rafael.NinjaAPI.missions;

import com.rafael.NinjaAPI.ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_mission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String difficulty;

    //@OneToMany Uma missão pode ter varios ninjas
    @OneToMany(mappedBy = "mission")
    private List<NinjaModel> ninjas;

}

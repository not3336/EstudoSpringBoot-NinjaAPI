package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.missions.MissionModel;
import jakarta.persistence.*;
import lombok.*;

//Entity transformar a classe em uma entidade do DB
@Entity
@Table(name = "tb_ninja")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    //@ManyToOne um ninja tem uma unica missão
    @ManyToOne
    @JoinColumn(name = "mission_id") //Foreign Key
    private MissionModel mission;

}


package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.missions.MissionModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDto{
    private Long id;
    private String name;
    private String email;
    private String imgUrl;
    private int age;
    private NinjaRank rank;
    private MissionModel mission;

}

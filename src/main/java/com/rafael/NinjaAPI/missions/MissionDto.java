package com.rafael.NinjaAPI.missions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MissionDto{
    private Long id;
    private String name;
    private String description;
    private MissionRank rank;
}

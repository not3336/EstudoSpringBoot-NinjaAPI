package com.rafael.NinjaAPI.ninjas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDto {
    private String name;
    private String email;
    private String imgUrl;
    private int age;
    private String rank;
    private Long missionId;

}

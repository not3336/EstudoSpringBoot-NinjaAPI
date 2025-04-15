package com.rafael.NinjaAPI.ninjas;

public record NinjaDto (String name, String email, String imgUrl, int age, NinjaRank rank, Long missionId) {
    public NinjaDto(String name, String email, String imgUrl, int age, NinjaRank rank, Long missionId) {
        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
        this.age = age;
        this.rank = rank;
        this.missionId = missionId;
    }
}

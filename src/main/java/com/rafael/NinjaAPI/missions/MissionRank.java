package com.rafael.NinjaAPI.missions;

public enum MissionRank {
    E(1),
    D(2),
    C(3),
    B(4),
    A(5),
    S(6);

    private final int difficulty;

    private MissionRank(int difficulty){
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}

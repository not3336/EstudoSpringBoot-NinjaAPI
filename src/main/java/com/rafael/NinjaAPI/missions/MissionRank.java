package com.rafael.NinjaAPI.missions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionRank {
    E(0),
    D(1),
    C(2),
    B(3),
    A(4),
    S(5);

    private final int rank;

}

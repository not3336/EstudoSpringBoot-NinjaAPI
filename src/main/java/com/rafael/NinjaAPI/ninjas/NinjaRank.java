package com.rafael.NinjaAPI.ninjas;

import com.rafael.NinjaAPI.ninjas.exceptions.NinjaRankNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NinjaRank{
    GENNIN(0),
    CHUNNIN(1),
    JONNIN(2),
    ANBU(3),
    KAGE(4),
    SANNIN(5),
    NUKENNIN(6),
    OININ(7)
    ;

    private final int rank;

}

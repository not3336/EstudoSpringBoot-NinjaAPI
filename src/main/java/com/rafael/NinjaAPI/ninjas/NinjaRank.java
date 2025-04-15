package com.rafael.NinjaAPI.ninjas;

public enum NinjaRank {
    GENNIN(1),
    CHUNNIN(2),
    JONNIN(3),
    ANBU(4),
    KAGE(5),
    SANNIN(6),
    NUKENNIN(7),
    OININ(8)
    ;

    private NinjaRank(int rankId){
        this.rankId = rankId;
    }

    private final int rankId;

    public int getRankId() {
        return rankId;
    }
}

package com.picksa.picksaserver.applicant;

public enum OrderCondition {

    STATUS,
    RESULT,
    SCORE;

    public static OrderCondition from(String condition) {
        return OrderCondition.valueOf(condition.toUpperCase());
    }

}

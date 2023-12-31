package com.picksa.picksaserver.global.domain;

import lombok.Getter;

@Getter
public enum Part {

    ALL("공통"),
    PM("기획"),
    DESIGN("디자인"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    private final String partName;

    Part(String partName) {
        this.partName = partName;
    }

    public static Part from(String lowerCase) {
        return Part.valueOf(lowerCase.toUpperCase());
    }

}

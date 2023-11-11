package com.picksa.picksaserver.manager;

public enum Position {

    PRESIDENT("회장단"),
    PART_LEADER("파트장"),
    GENERAL("일반");

    private final String positionName;

    Position(String positionName) {
        this.positionName = positionName;
    }

}

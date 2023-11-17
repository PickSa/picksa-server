package com.picksa.picksaserver.global.domain;

import lombok.Getter;

import java.time.Year;
import java.util.Arrays;

@Getter
public enum Generation {

    ELEVENTH(11, Year.of(2023)),
    TWELFTH(12, Year.of(2024));

    private final int number;
    private final Year year;

    Generation(int number, Year year) {
        this.number = number;
        this.year = year;
    }

    public static Generation from(Year year) {
        return Arrays.stream(Generation.values())
                .filter(v -> v.getYear().equals(year))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d년도 기수에 해당하는 값이 없습니다.", year.getValue())));
    }

}

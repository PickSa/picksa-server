package com.picksa.picksaserver.application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class CommonAnswers {

    @Column(length = 1000)
    private String commonAnswer1;

    @Column(length = 1000)
    private String commonAnswer2;

    @Column(length = 1000)
    private String commonAnswer3;

    @Column(length = 1000)
    private String commonAnswer4;

    @Column(length = 1000)
    private String commonAnswer5;

}

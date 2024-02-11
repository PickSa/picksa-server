package com.picksa.picksaserver.application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PartAnswers {

    @Column(length = 1000)
    private String partAnswer1;

    @Column(length = 1000)
    private String partAnswer2;

    @Column(length = 1000)
    private String partAnswer3;

    private String portfolio;

}

package com.picksa.picksaserver.application.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PersonalData {

    private String name;

    private String email;

    private String major;

    private String studentId;

    private String multiMajor;

    private String semester;

    private String phone;

    private String gender;

}

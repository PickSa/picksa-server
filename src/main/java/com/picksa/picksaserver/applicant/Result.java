package com.picksa.picksaserver.applicant;

public enum Result {

    PENDING("미정"),
    FAILURE("불합격"),
    PASS("합격");

    private final String resultName;

    Result(String resultName) {
        this.resultName = resultName;
    }

}

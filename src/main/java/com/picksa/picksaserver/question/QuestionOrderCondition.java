package com.picksa.picksaserver.question;

public enum QuestionOrderCondition {

    LASTEST,
    TAG;

    public static QuestionOrderCondition from(String condition) {
        return QuestionOrderCondition.valueOf(condition.toUpperCase());
    }
}

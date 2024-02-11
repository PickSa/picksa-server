package com.picksa.picksaserver.application.repository.converter;

import jakarta.persistence.AttributeConverter;

public class AgreementConverter implements AttributeConverter<Boolean, String> {

    private static final String YES = "예";
    private static final String NO = "아니오";

    @Override
    public String convertToDatabaseColumn(Boolean isAgreed) {
        if (isAgreed) {
            return YES;
        }
        return NO;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s.equals(YES)) {
            return true;
        }
        return false;
    }

}

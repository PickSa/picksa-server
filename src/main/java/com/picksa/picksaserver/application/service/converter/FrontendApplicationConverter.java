package com.picksa.picksaserver.application.service.converter;

import com.picksa.picksaserver.application.domain.ApplicationEntity;
import com.picksa.picksaserver.application.domain.OriginalApplicationEntity;
import com.picksa.picksaserver.global.domain.Part;
import org.springframework.stereotype.Component;

@Component
public class FrontendApplicationConverter implements ApplicationCoverter {
    @Override
    public Part part() {
        return Part.FRONTEND;
    }

    @Override
    public ApplicationEntity convertOriginalToApplication(OriginalApplicationEntity original, String interviewAvailables, Long originalId, int generation) {

        // TODO: 2024-01-20 기수 자동 적용
        return ApplicationEntity.builder()
                .agreePrivacyCollection(original.isAgreePrivacyCollection())
                .part(original.getPart())
                .generation(generation)
                .personalData(original.getPersonalData())
                .commonAnswers(original.getCommonAnswer())
                .partAnswers(original.getFrontend())
                .interviewAvailableTimes(interviewAvailables)
                .originalId(originalId)
                .build();
    }
}

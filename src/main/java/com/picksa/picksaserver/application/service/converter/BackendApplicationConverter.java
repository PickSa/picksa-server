package com.picksa.picksaserver.application.service.converter;

import com.picksa.picksaserver.application.domain.ApplicationEntity;
import com.picksa.picksaserver.application.domain.OriginalApplicationEntity;
import com.picksa.picksaserver.global.domain.Part;
import org.springframework.stereotype.Component;

@Component
public class BackendApplicationConverter implements ApplicationCoverter {

    @Override
    public Part part() {
        return Part.BACKEND;
    }

    @Override
    public ApplicationEntity convertOriginalToApplication(OriginalApplicationEntity original, String interviewAvailables, Long originalId, int generation) {

        return ApplicationEntity.builder()
                .agreePrivacyCollection(original.isAgreePrivacyCollection())
                .part(original.getPart())
                .generation(generation)
                .personalData(original.getPersonalData())
                .commonAnswers(original.getCommonAnswer())
                .partAnswers(original.getBackend())
                .interviewAvailableTimes(interviewAvailables)
                .originalId(originalId)
                .build();
    }

}

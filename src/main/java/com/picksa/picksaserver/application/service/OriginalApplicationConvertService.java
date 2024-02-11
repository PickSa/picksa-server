package com.picksa.picksaserver.application.service;


import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;
import com.picksa.picksaserver.applicant.repository.InterviewScheduleRepository;
import com.picksa.picksaserver.application.domain.ApplicationEntity;
import com.picksa.picksaserver.application.service.converter.InterviewAvailableConverter;
import com.picksa.picksaserver.application.domain.OriginalApplicationEntity;
import com.picksa.picksaserver.application.service.converter.ApplicationConverterProvider;
import com.picksa.picksaserver.application.service.converter.ApplicationCoverter;
import com.picksa.picksaserver.application.repository.ApplicationRepository;
import com.picksa.picksaserver.application.repository.OriginalApplicationRepository;
import com.picksa.picksaserver.global.domain.Generation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OriginalApplicationConvertService {

    private final OriginalApplicationRepository originalApplicationRepository;
    private final InterviewScheduleRepository interviewScheduleRepository;
    private final ApplicationRepository applicationRepository;

    private final ApplicationConverterProvider applicationConverterProvider;
    private final InterviewAvailableConverter interviewAvailableConverter;

    @Transactional
    public void convertOriginalToApplication() {

        ApplicationEntity lastApplication = applicationRepository.findTopByOrderByOriginalIdDesc();
        Long lastOriginalId = 0L;
        if (lastApplication != null) {
            lastOriginalId = lastApplication.getOriginalId();
        }

        int generation = Generation.getGenerationOfThisYear();
        List<InterviewScheduleEntity> interviewSchedules = interviewScheduleRepository.findByGenerationOrderByDate(generation);

        List<OriginalApplicationEntity> originals = originalApplicationRepository.findByIdAfter(lastOriginalId);

        List<ApplicationEntity> applicationsToSave = new ArrayList<>();
        for (OriginalApplicationEntity original : originals) {
            ApplicationCoverter applicationCoverter = applicationConverterProvider.provideConverter(original.getPart());
            String formattedInterviewAvailable = interviewAvailableConverter.formatTotalToBinary(original.getInterviewAvailable(), interviewSchedules);
            System.out.println("formattedInterviewAvailable = " + formattedInterviewAvailable);
            ApplicationEntity application = applicationCoverter.convertOriginalToApplication(original, formattedInterviewAvailable, original.getId(), generation);

            applicationsToSave.add(application);
        }

        applicationRepository.saveAll(applicationsToSave);
    }

}

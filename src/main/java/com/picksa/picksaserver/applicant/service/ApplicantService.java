package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantAllResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.applicant.repository.ApplicantQueryRepository;
import com.picksa.picksaserver.global.domain.Generation;
import com.picksa.picksaserver.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantQueryRepository applicantQueryRepository;
    private final ManagerRepository managerRepository;

    private final Year thisYear = Year.now();

    public ApplicantAllResponse getAllApplicants(OrderCondition orderCondition) {
        int generation = Generation.from(thisYear).getNumber();
        int managerCount = managerRepository.countByGeneration(generation);
        List<ApplicantResponse> applicants = applicantQueryRepository.findAllApplicants(orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                managerCount,
                applicants
        );
    }

}

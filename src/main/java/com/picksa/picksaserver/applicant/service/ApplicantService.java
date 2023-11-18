package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantAllResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.applicant.repository.ApplicantQueryRepository;
import com.picksa.picksaserver.global.domain.Generation;
import com.picksa.picksaserver.global.domain.Part;
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

    public ApplicantAllResponse getAllApplicants(OrderCondition orderCondition) {
        int generation = getGeneration();
        int managerCount = getManagerCount(generation);
        List<ApplicantResponse> applicants = applicantQueryRepository.findAllApplicants(orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                managerCount,
                applicants
        );
    }

    public ApplicantAllResponse getApplicantsByPart(Part part, OrderCondition orderCondition) {
        int generation = getGeneration();
        int managerCount = getManagerCount(generation);
        List<ApplicantResponse> applicants = applicantQueryRepository.findApplicantsByPart(part, orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                managerCount,
                applicants
        );
    }

    private int getGeneration() {
        Year year = Year.now();
        return Generation.from(year).getNumber();
    }

    private int getManagerCount(int generation) {
        return managerRepository.countByGeneration(generation);
    }

}

package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantAllResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.applicant.repository.ApplicantQueryRepository;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.picksa.picksaserver.global.domain.Generation.getGenerationOfThisYear;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantQueryRepository applicantQueryRepository;
    private final ManagerRepository managerRepository;

    @Transactional(readOnly = true)
    public ApplicantAllResponse getAllApplicants(OrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        int managerCount = getManagerCount(generation);
        List<ApplicantResponse> applicants = applicantQueryRepository.findAllApplicants(orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                managerCount,
                applicants
        );
    }

    @Transactional(readOnly = true)
    public ApplicantAllResponse getApplicantsByPart(Part part, OrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        int managerCount = getManagerCount(generation);
        List<ApplicantResponse> applicants = applicantQueryRepository.findApplicantsByPart(part, orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                managerCount,
                applicants
        );
    }

    private int getManagerCount(int generation) {
        return managerRepository.countByGeneration(generation);
    }

}

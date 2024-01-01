package com.picksa.picksaserver.applicant.repository;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantScheduleResponse;
import com.picksa.picksaserver.global.domain.Part;

import java.util.List;


public interface ApplicantQueryRepository {

    List<ApplicantResponse> findAllApplicants(OrderCondition orderCondition, int generation);

    List<ApplicantResponse> findApplicantsByPart(Part part, OrderCondition orderCondition, int generation);

    List<ApplicantScheduleResponse> findApplicantSchedules(int generation);
}

package com.picksa.picksaserver.applicant.repository;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;

import java.util.List;


public interface ApplicantQueryRepository {

    List<ApplicantResponse> findAllApplicants(OrderCondition orderCondition, int generation);

}

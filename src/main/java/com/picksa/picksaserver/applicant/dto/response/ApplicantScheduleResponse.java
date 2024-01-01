package com.picksa.picksaserver.applicant.dto.response;

import com.picksa.picksaserver.global.domain.Part;

public record ApplicantScheduleResponse(
        Long id,
        String name,
        Part part,
        String available
) {
}

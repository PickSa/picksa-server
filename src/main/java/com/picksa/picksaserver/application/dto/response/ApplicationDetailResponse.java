package com.picksa.picksaserver.application.dto.response;

import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.application.domain.ApplicationEntity;
import com.picksa.picksaserver.global.domain.Part;
import lombok.Builder;

@Builder
public record ApplicationDetailResponse(
        String name,
        String studentId,
        String email,
        String gender,
        String major,
        String multiMajor,
        String phone,
        String semester,
        String portfolio,
        Part part,
        int generation,
        String commonAnswer1,
        String commonAnswer2,
        String commonAnswer3,
        String commonAnswer4,
        String commonAnswer5,
        String partAnswer1,
        String partAnswer2,
        String partAnswer3,
        String interviewAvailableTimes
) {
    public static ApplicationDetailResponse from(ApplicationEntity application) {
        return ApplicationDetailResponse.builder()
                .name(application.getPersonalData().getName())
                .studentId(application.getPersonalData().getStudentId())
                .email(application.getPersonalData().getEmail())
                .gender(application.getPersonalData().getGender())
                .major(application.getPersonalData().getMajor())
                .multiMajor(application.getPersonalData().getMultiMajor())
                .phone(application.getPersonalData().getPhone())
                .semester(application.getPersonalData().getSemester())
                .part(application.getPart())
                .generation(application.getGeneration())
                .commonAnswer1(application.getCommonAnswers().getCommonAnswer1())
                .commonAnswer2(application.getCommonAnswers().getCommonAnswer2())
                .commonAnswer3(application.getCommonAnswers().getCommonAnswer3())
                .commonAnswer4(application.getCommonAnswers().getCommonAnswer4())
                .commonAnswer5(application.getCommonAnswers().getCommonAnswer5())
                .partAnswer1(application.getPartAnswers().getPartAnswer1())
                .partAnswer2(application.getPartAnswers().getPartAnswer2())
                .partAnswer3(application.getPartAnswers().getPartAnswer3())
                .interviewAvailableTimes(application.getInterviewAvailableTimes())
                .build();
    }

    public ApplicantEntity toEntity() {
        return ApplicantEntity.builder()
                .name(name)
                .studentId(studentId)
                .semester(semester)
                .gender(gender)
                .phone(phone)
                .email(email)
                .major(major)
                .multiMajor(multiMajor)
                .part(part)
                .generation(generation)
                .interviewAvailableTimes(interviewAvailableTimes)
                .build();
    }

}

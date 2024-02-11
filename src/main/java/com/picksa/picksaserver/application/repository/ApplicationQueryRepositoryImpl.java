package com.picksa.picksaserver.application.repository;

import com.picksa.picksaserver.application.dto.response.ApplicationDetailResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.picksa.picksaserver.application.domain.QApplicationEntity.applicationEntity;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ApplicationDetailResponse> getApplicationCreatedBetween(LocalDateTime after, LocalDateTime until) {
        return jpaQueryFactory.select(Projections.constructor(
                        ApplicationDetailResponse.class,
                        applicationEntity.personalData.name,
                        applicationEntity.personalData.studentId,
                        applicationEntity.personalData.email,
                        applicationEntity.personalData.gender,
                        applicationEntity.personalData.major,
                        applicationEntity.personalData.multiMajor,
                        applicationEntity.personalData.phone,
                        applicationEntity.personalData.semester,
                        applicationEntity.partAnswers.portfolio,
                        applicationEntity.part,
                        applicationEntity.generation,
                        applicationEntity.commonAnswers.commonAnswer1,
                        applicationEntity.commonAnswers.commonAnswer2,
                        applicationEntity.commonAnswers.commonAnswer3,
                        applicationEntity.commonAnswers.commonAnswer4,
                        applicationEntity.commonAnswers.commonAnswer5,
                        applicationEntity.partAnswers.partAnswer1,
                        applicationEntity.partAnswers.partAnswer2,
                        applicationEntity.partAnswers.partAnswer3,
                        applicationEntity.interviewAvailableTimes
                ))
                .from(applicationEntity)
                .where(
                        applicationEntity.createdAt.after(after)
                                .orAllOf(applicationEntity.createdAt.before(until),
                                        applicationEntity.createdAt.eq(until)
                                )
                )
                .orderBy(
                        applicationEntity.createdAt.desc()
                )
                .fetch();
    }

    @Override
    public List<ApplicationDetailResponse> getApplicationsUntil(LocalDateTime until) {
        return jpaQueryFactory.select(Projections.constructor(
                        ApplicationDetailResponse.class,
                        applicationEntity.personalData.name,
                        applicationEntity.personalData.studentId,
                        applicationEntity.personalData.email,
                        applicationEntity.personalData.gender,
                        applicationEntity.personalData.major,
                        applicationEntity.personalData.multiMajor,
                        applicationEntity.personalData.phone,
                        applicationEntity.personalData.semester,
                        applicationEntity.partAnswers.portfolio,
                        applicationEntity.part,
                        applicationEntity.generation,
                        applicationEntity.commonAnswers.commonAnswer1,
                        applicationEntity.commonAnswers.commonAnswer2,
                        applicationEntity.commonAnswers.commonAnswer3,
                        applicationEntity.commonAnswers.commonAnswer4,
                        applicationEntity.commonAnswers.commonAnswer5,
                        applicationEntity.partAnswers.partAnswer1,
                        applicationEntity.partAnswers.partAnswer2,
                        applicationEntity.partAnswers.partAnswer3,
                        applicationEntity.interviewAvailableTimes
                ))
                .from(applicationEntity)
                .where(
                        applicationEntity.createdAt.before(until)
                                .or(applicationEntity.createdAt.eq(until))
                )
                .orderBy(
                        applicationEntity.createdAt.desc()
                )
                .fetch();
    }

}

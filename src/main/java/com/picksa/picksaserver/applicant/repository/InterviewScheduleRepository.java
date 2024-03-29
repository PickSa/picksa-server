package com.picksa.picksaserver.applicant.repository;

import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewScheduleRepository extends JpaRepository<InterviewScheduleEntity, Long> {

    List<InterviewScheduleEntity> findByGenerationOrderByDate(int generation);

    void deleteAllByGeneration(int generation);

}

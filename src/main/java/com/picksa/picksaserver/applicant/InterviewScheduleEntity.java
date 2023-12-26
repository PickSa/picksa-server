package com.picksa.picksaserver.applicant;

import com.picksa.picksaserver.global.domain.Generation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "interview_schedules")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int generation;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startAt;

    @Column
    private LocalTime finishAt;

    @Builder
    public InterviewScheduleEntity(LocalDate date, LocalTime startAt, LocalTime finishAt) {
        this.generation = Generation.getGenerationOfThisYear();
        this.date = date;
        this.startAt = startAt;
        this.finishAt = finishAt;
    }

}

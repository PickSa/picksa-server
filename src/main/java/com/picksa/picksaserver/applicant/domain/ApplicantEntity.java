package com.picksa.picksaserver.applicant.domain;

import com.picksa.picksaserver.global.domain.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "applicants")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 8)
    private String studentId;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String major;

    @Column
    private String multiMajor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(nullable = false)
    private int generation;

    @Column
    private String portfolio;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private boolean isEvaluated;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(nullable = false, length = 120)
    private String interviewAvailableTimes;

    @Builder
    public ApplicantEntity(String name,
                           String studentId,
                           String semester,
                           String gender,
                           String phone,
                           String email,
                           String major,
                           String multiMajor,
                           Part part,
                           int generation,
                           String portfolio,
                           boolean isEvaluated,
                           String interviewAvailableTimes) {
        this.name = name;
        this.studentId = studentId;
        this.semester = semester;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.major = major;
        this.multiMajor = multiMajor;
        this.part = part;
        this.generation = generation;
        this.portfolio = portfolio;
        this.score = 0;
        this.isEvaluated = isEvaluated;
        this.result = Result.PENDING;
        this.interviewAvailableTimes = interviewAvailableTimes;
    }

    public void upScore() {
        this.score++;
    }

    public void downScore() {
        this.score--;
    }

    public void evaluationDone() {
        this.isEvaluated = true;
    }

    public void decideResult(Result decide) {
        this.result = decide;
    }

}

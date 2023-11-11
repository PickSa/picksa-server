package com.picksa.picksaserver.applicant;

import com.picksa.picksaserver.global.domain.Part;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Part part;

    @Column(nullable = false)
    private int generation;

    @Column(nullable = false)
    private String portfolio;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int evaluator;

    @Column(nullable = false)
    private boolean isEvaluated;

    @Column(nullable = false)
    private Result result;

    @ElementCollection
    @CollectionTable(name = "interview_availables", joinColumns = @JoinColumn(name = "applicant_id"))
    private List<InterviewAvailableTime> interviewAvailableTimes;

    @Builder
    public ApplicantEntity(String name, String studentId, String semester, String gender, String phone, String email, String major, String multiMajor, Part part, int generation, String portfolio, int score, int evaluator, boolean isEvaluated, Result result, List<InterviewAvailableTime> interviewAvailableTimes) {
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
        this.score = score;
        this.evaluator = evaluator;
        this.isEvaluated = isEvaluated;
        this.result = result;
        this.interviewAvailableTimes = interviewAvailableTimes;
    }

}
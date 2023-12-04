package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.manager.ManagerEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "evaluations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private boolean pass;

    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicantEntity applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private ManagerEntity writer;

    @Builder
    public EvaluationEntity(String comment, boolean pass, ApplicantEntity applicant, ManagerEntity writer) {
        this.comment = comment;
        this.pass = pass;
        this.applicant = applicant;
        this.writer = writer;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void updatePass(boolean pass) {
        this.pass = pass;
    }

}

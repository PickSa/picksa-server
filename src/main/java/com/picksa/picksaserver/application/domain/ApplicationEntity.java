package com.picksa.picksaserver.application.domain;

import com.picksa.picksaserver.application.domain.CommonAnswers;
import com.picksa.picksaserver.application.domain.PartAnswers;
import com.picksa.picksaserver.application.domain.PersonalData;
import com.picksa.picksaserver.global.domain.BaseEntity;
import com.picksa.picksaserver.global.domain.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

@Entity
@Getter
@Table(name = "applications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean agreePrivacyCollection;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Column
    private int generation;

    @Embedded
    private PersonalData personalData;

    @Embedded
    private CommonAnswers commonAnswers;

    @Embedded
    private PartAnswers partAnswers;

    @Column
    private String interviewAvailableTimes;

    @Column
    private Long originalId;

    @Builder
    public ApplicationEntity(boolean agreePrivacyCollection, Part part, int generation, PersonalData personalData, CommonAnswers commonAnswers, PartAnswers partAnswers, String interviewAvailableTimes, Long originalId) {
        this.agreePrivacyCollection = agreePrivacyCollection;
        this.part = part;
        this.personalData = personalData;
        this.commonAnswers = commonAnswers;
        this.partAnswers = partAnswers;
        this.interviewAvailableTimes = interviewAvailableTimes;
        this.generation = generation;
        this.originalId = originalId;
    }

}

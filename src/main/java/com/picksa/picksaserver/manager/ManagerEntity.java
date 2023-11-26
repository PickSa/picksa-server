package com.picksa.picksaserver.manager;

import com.picksa.picksaserver.global.domain.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
>>>>>>> 318e3c5 (fix: 운영진, 평가자 매핑 관계 수정 및 중복 평가 예외처리)
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "managers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int generation;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    @OneToMany(mappedBy = "writer")
    private final List<EvaluationEntity> evaluations = new ArrayList<>();

>>>>>>> 318e3c5 (fix: 운영진, 평가자 매핑 관계 수정 및 중복 평가 예외처리)
=======
>>>>>>> 1da7844 (fix: 평가-운영진 다대일  매핑 단방향으로 수정)
=======
>>>>>>> 5516eab (fix: 평가-운영진 다대일  매핑 단방향으로 수정)
    @Builder
    public ManagerEntity(int generation, String name, Part part, Position position) {
        this.generation = generation;
        this.name = name;
        this.part = part;
        this.position = position;
    }

}

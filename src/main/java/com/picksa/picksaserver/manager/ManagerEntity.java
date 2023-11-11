package com.picksa.picksaserver.manager;

import com.picksa.picksaserver.global.domain.Part;
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
    private Part part;

    @Column(nullable = false)
    private Position position;

    @Builder
    public ManagerEntity(int generation, String name, Part part, Position position) {
        this.generation = generation;
        this.name = name;
        this.part = part;
        this.position = position;
    }

}

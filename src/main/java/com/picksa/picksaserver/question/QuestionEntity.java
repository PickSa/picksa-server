package com.picksa.picksaserver.question;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.manager.ManagerEntity;
import jakarta.persistence.Column;
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
@Table(name = "questions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int sequence;

    @Column(nullable = false)
    private Part part;

    @Column(nullable = false)
    private boolean isDetermined;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private ManagerEntity writer;

    @Builder
    public QuestionEntity(int sequence, Part part, boolean isDetermined, String content, TagEntity tag, ManagerEntity writer) {
        this.sequence = sequence;
        this.part = part;
        this.isDetermined = isDetermined;
        this.content = content;
        this.tag = tag;
        this.writer = writer;
    }

}


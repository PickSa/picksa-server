package com.picksa.picksaserver.question;

import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.response.QuestionUpdateSequenceResponse;
import com.picksa.picksaserver.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int sequence;

    @Column(nullable = false)
    private boolean isDetermined;

    private String content;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private UserEntity writer;

    @Builder
    public QuestionEntity(int sequence, boolean isDetermined, String content, TagEntity tag, UserEntity writer) {
        this.sequence = sequence;
        this.isDetermined = isDetermined;
        this.content = content;
        this.tag = tag;
        this.writer = writer;
    }

    public QuestionDetermine updateIsDetermined(boolean isDetermined) {
        this.isDetermined = isDetermined;
        return new QuestionDetermine(this.id, this.isDetermined);
    }

    public void deleteQuestion() {
        this.deletedAt = LocalDateTime.now();
    }

    public QuestionUpdateSequenceResponse updateSequence(int sequence) {
        this.sequence = sequence;
        return new QuestionUpdateSequenceResponse(this.id, this.sequence);
    }
}


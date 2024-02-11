package com.picksa.picksaserver.sync.domain;

import com.picksa.picksaserver.global.domain.BaseEntity;
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

import java.time.LocalDateTime;

@Table(name = "sync_request_logs")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyncRequestLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "processed_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime processedAt;

    @Builder
    public SyncRequestLog(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

}

package com.picksa.picksaserver.sync.repository;

import com.picksa.picksaserver.sync.domain.SyncRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyncLogRepository extends JpaRepository<SyncRequestLog, Long> {

    SyncRequestLog getFirstByOrderByCreatedAtDesc();

}

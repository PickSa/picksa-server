package com.picksa.picksaserver.application.repository;

import com.picksa.picksaserver.application.domain.OriginalApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OriginalApplicationRepository extends JpaRepository<OriginalApplicationEntity, Long> {

    List<OriginalApplicationEntity> findByIdAfter(Long id);

}

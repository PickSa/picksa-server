package com.picksa.picksaserver.application.repository;

import com.picksa.picksaserver.application.domain.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long>, ApplicationQueryRepository {

    ApplicationEntity findTopByOrderByOriginalIdDesc();

}

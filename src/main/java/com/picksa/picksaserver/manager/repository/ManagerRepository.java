package com.picksa.picksaserver.manager.repository;

import com.picksa.picksaserver.manager.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {
    int countByGeneration(int generation);

}

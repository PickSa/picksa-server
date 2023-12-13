package com.picksa.picksaserver.question.repository;

import com.picksa.picksaserver.question.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>, QuestionQueryRepository {
}

package com.picksa.picksaserver.question.repository;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.QuestionOrderCondition;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionQueryRepository {

    List<QuestionResponse> findAllQuestionsByPart(Part part, QuestionOrderCondition condition, int generation);

    List<QuestionResponse> findDeterminedQuestionsByPart(Part part, int generation);

    List<QuestionEntity> findDeterminedQuestionsByGeneration(int generation);

}

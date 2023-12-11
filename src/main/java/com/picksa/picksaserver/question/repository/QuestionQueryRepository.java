package com.picksa.picksaserver.question.repository;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionOrderCondition;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionQueryRepository {

    // 파트별 질문 리스트 조회(확정 여부에 관계 없이)
    List<QuestionResponse> findAllQuestionsByPart(Part part, QuestionOrderCondition condition, int generation);

    // 파트별 확정된 질문 리스트 조회
}

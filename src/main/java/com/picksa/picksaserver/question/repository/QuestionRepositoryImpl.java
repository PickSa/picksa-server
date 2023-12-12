package com.picksa.picksaserver.question.repository;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionOrderCondition;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.picksa.picksaserver.question.QQuestionEntity.questionEntity;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionRepositoryImpl implements QuestionQueryRepository {

    /*
    사용자는 QuestionRepository 인터페이스를 DI 받아서 사용한다.
    파일명이 Repository interface 이름 + Impl 인 클래스를 찾아서 -> interface에 JpaRepository를 주입할 때 Impl 객체를 삽입해준다.
    => 따라서 반드시 Impl 클래스는 interface 이름 + Impl로 작성해야 한다.
     */

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QuestionResponse> findAllQuestionsByPart(Part part, QuestionOrderCondition condition, int generation) {
        JPAQuery<QuestionResponse> query = jpaQueryFactory.select(Projections.constructor(
                        QuestionResponse.class,
                        questionEntity.id,
                        questionEntity.sequence,
                        questionEntity.isDetermined,
                        questionEntity.content,
                        questionEntity.tag.id,
                        questionEntity.tag.content,
                        questionEntity.writer.id,
                        questionEntity.writer.name,
                        questionEntity.createdAt))
                .from(questionEntity)
                .where(questionEntity.tag.part.eq(part),
                        questionEntity.tag.generation.eq(generation));

        if (condition == QuestionOrderCondition.LASTEST) {
            query.orderBy(questionEntity.isDetermined.desc(), questionEntity.createdAt.desc());
        } else if (condition == QuestionOrderCondition.TAG) {
            query.orderBy(questionEntity.isDetermined.desc(), questionEntity.tag.id.asc(), questionEntity.createdAt.desc());
        }

        return query.fetch();
    }

    @Override
    public List<QuestionResponse> findDeterminedQuestionsByPart(Part part, int generation) {
            return jpaQueryFactory.select(Projections.constructor(
                            QuestionResponse.class,
                            questionEntity.id,
                            questionEntity.sequence,
                            questionEntity.isDetermined,
                            questionEntity.content,
                            questionEntity.tag.id,
                            questionEntity.tag.content,
                            questionEntity.writer.id,
                            questionEntity.writer.name,
                            questionEntity.createdAt))
                    .from(questionEntity)
                    .where(questionEntity.tag.part.eq(part)
                                    .or(questionEntity.tag.part.eq(Part.ALL)),
                            questionEntity.tag.generation.eq(generation),
                            questionEntity.isDetermined.eq(true))
                    .orderBy(
                            new CaseBuilder()
                                    .when(questionEntity.tag.part.eq(Part.ALL))
                                    .then(0)
                                    .otherwise(1).asc(),
                            questionEntity.tag.part.asc(),
                            questionEntity.sequence.asc())
                    .fetch();
    }
}

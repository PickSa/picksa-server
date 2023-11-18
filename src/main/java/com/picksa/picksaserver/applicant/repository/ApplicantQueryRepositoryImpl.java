package com.picksa.picksaserver.applicant.repository;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.global.domain.Part;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.picksa.picksaserver.applicant.QApplicantEntity.applicantEntity;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantQueryRepositoryImpl implements ApplicantQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final StringTemplate partOrder = Expressions.stringTemplate(
            "FIELD({0}, 'PM', 'DESIGN', 'FRONTEND', 'BACKEND')",
            applicantEntity.part
    );

    @Override
    public List<ApplicantResponse> findAllApplicants(OrderCondition orderCondition, int generation) {
        return jpaQueryFactory.select(Projections.constructor(
                        ApplicantResponse.class,
                        applicantEntity.id,
                        applicantEntity.part,
                        applicantEntity.name,
                        applicantEntity.studentId,
                        applicantEntity.score,
                        applicantEntity.isEvaluated,
                        applicantEntity.result))
                .from(applicantEntity)
                .where(applicantEntity.generation.eq(generation))
                .orderBy(
                        partOrder.asc(),
                        orderByField(orderCondition),
                        applicantEntity.name.asc())
                .fetch();
    }

    @Override
    public List<ApplicantResponse> findApplicantsByPart(Part part, OrderCondition orderCondition, int generation) {
        return jpaQueryFactory.select(Projections.constructor(
                        ApplicantResponse.class,
                        applicantEntity.id,
                        applicantEntity.part,
                        applicantEntity.name,
                        applicantEntity.studentId,
                        applicantEntity.score,
                        applicantEntity.isEvaluated,
                        applicantEntity.result))
                .from(applicantEntity)
                .where(applicantEntity.generation.eq(generation),
                        applicantEntity.part.eq(part))
                .orderBy(
                        orderByField(orderCondition),
                        applicantEntity.name.asc()
                )
                .fetch();
    }

    private OrderSpecifier<?> orderByField(OrderCondition condition) {

        if (condition == null) return OrderByNull.getDefault();

        if (condition.equals(OrderCondition.RESULT)) {
            return new OrderSpecifier<>(Order.DESC, applicantEntity.result);
        } else if (condition.equals(OrderCondition.STATUS)) {
            return new OrderSpecifier<>(Order.ASC, applicantEntity.isEvaluated);
        } else if (condition.equals(OrderCondition.SCORE)) {
            return new OrderSpecifier<>(Order.DESC, applicantEntity.score);
        }

        throw new IllegalArgumentException(String.format("일치하는 정렬 조건이 없습니다:%s", condition.name()));
    }

    public static class OrderByNull extends OrderSpecifier {

        private static final OrderByNull DEFAULT = new OrderByNull();

        private OrderByNull() {
            super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
        }

        public static OrderByNull getDefault() {
            return OrderByNull.DEFAULT;
        }
    }

}

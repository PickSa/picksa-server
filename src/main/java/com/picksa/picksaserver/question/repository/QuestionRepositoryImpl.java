package com.picksa.picksaserver.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}

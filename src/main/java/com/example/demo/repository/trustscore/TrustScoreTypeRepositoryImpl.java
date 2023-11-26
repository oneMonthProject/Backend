package com.example.demo.repository.trustscore;

import com.example.demo.model.QProject;
import com.example.demo.model.QTrustGrade;
import com.example.demo.model.trustscore.QTrustScoreType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrustScoreTypeRepositoryImpl implements TrustScoreTypeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int getScore(Long trustScoreTypeId) {
        // QClass 선언
        QTrustScoreType trustScoreType = QTrustScoreType.trustScoreType;
        // 조건 설정
        BooleanBuilder condition = new BooleanBuilder();
        condition.and(trustScoreType.id.eq(trustScoreTypeId));
        // 쿼리 수행
        return jpaQueryFactory
                .select(trustScoreType.score)
                .from(trustScoreType)
                .where(condition)
                .fetchFirst();
    }

    @Override
    public int getScoreByProject(Long projectId, Long trustScoreTypeId) {
        QTrustScoreType trustScoreType = QTrustScoreType.trustScoreType;
        QTrustGrade trustGrade = QTrustGrade.trustGrade;
        QProject project = QProject.project;
        return jpaQueryFactory
                .select(new CaseBuilder()
                        .when(trustScoreType.gubunCode.eq("M"))
                        .then(trustScoreType.score.multiply(-1))
                        .otherwise(trustScoreType.score))
                .from(project)
                .join(trustGrade)
                .on(trustGrade.id.eq(project.trustGrade.id))
                .join(trustScoreType)
                .on(trustScoreType.upTrustScoreTypeId.eq(trustScoreTypeId)
                        .and(trustScoreType.trustGradeName.eq(trustGrade.name)))
                .where(project.id.eq(projectId))
                .fetchFirst();
    }
}

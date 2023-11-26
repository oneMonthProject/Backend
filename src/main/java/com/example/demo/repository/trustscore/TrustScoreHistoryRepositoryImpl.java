package com.example.demo.repository.trustscore;

import com.example.demo.dto.trustscorehistory.ProjectUserHistoryDto;
import com.example.demo.model.trustscore.QTrustScoreHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Repository
@RequiredArgsConstructor
public class TrustScoreHistoryRepositoryImpl implements TrustScoreHistoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    /**
     * 특정 프로젝트에 참여하는 사용자의, 해당 프로젝트 관련 발생한 신뢰점수이력을 DTO 형태로 반환
     * @param projectId
     * @param userId
     * @return List<ProjectUserHistoryDto>
     */
    @Override
    public List<ProjectUserHistoryDto> getProjectUserHistory(Long projectId, Long userId) {
        QTrustScoreHistory trustScoreHistory = QTrustScoreHistory.trustScoreHistory;
        return jpaQueryFactory
                .select(trustScoreHistory.workId, trustScoreHistory.score)
                .from(trustScoreHistory)
                .where(trustScoreHistory.projectId.eq(projectId)
                        .and(trustScoreHistory.userId.eq(userId)))
                .orderBy(trustScoreHistory.createDate.asc())
                .fetch()
                .stream()
                .map(history -> ProjectUserHistoryDto.builder()
                        .workId(history.get(trustScoreHistory.workId))
                        .score(history.get(trustScoreHistory.score))
                        .build())
                .collect(Collectors.toList());
    }
}

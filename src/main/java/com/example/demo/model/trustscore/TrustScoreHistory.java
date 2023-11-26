package com.example.demo.model.trustscore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
/**
 * com.example.demo.model.trustscore.TrustScoreHistory
 * <p>
 * 신뢰점수이력 Entity
 *
 * @author joo
 * @version 1.0
 * @since 2023/11/19
 * </p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------    ---------------------------
 *  2023/11/19     joo        최초 생성
 * </pre>
 */
@Entity
@Table(name = "trust_score_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class TrustScoreHistory implements Serializable {
    @Serial
    private static final long serialVersionUID = 9178194662912666071L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trust_score_history_id")
    Long id;
    /**
     * 사용자 자동생성 식별자
     */
    @Column
    private Long userId;
    /**
     * 신뢰점수타입 자동생성 식별자
     */
    @Column
    private Long trustScoreTypeId;
    /**
     * 프로젝트 자동생성 식별자
     */
    @Column
    private Long projectId;
    /**
     * 마일스톤 자동생성 식별자
     */
    @Column
    private Long milestoneId;
    /**
     * 업무 자동생성 식별자
     */
    @Column
    private Long workId;
    /**
     * 신뢰점수 증감
     */
    @Column
    private int score;
    /**
     * 생성날짜
     */
    @Column
    private Date createDate;
    @Builder
    public TrustScoreHistory(Long userId, Long trustScoreTypeId,
                             Long projectId, Long milestoneId,
                             int score, Long workId, Date createDate) {
        this.userId = userId;
        this.trustScoreTypeId =trustScoreTypeId;
        this.projectId = projectId;
        this.milestoneId = milestoneId;
        this.workId = workId;
        this.score = score;
        this.createDate = createDate;
    }
}
package com.example.demo.model.trust_score;

import java.util.Date;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "trust_score")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class TrustScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trust_score_id")
    private Long id;
    /** 유저자동생성식별자 */
    @Column // PK
    private Long userId;

    /** 유저신뢰점수값 */
    @Column private int score;

    /** 변경날짜 */
    @Column private Date updateDate;

    @Builder
    public TrustScore(Long userId, int score) {
        this.userId = userId;
        this.score = score;
        this.updateDate = new Date();
    }
}

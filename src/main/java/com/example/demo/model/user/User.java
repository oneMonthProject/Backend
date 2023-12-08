package com.example.demo.model.user;

import com.example.demo.constant.Role;
import com.example.demo.global.common.BaseTimeEntity;
import com.example.demo.model.position.Position;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.example.demo.model.trust_score.TrustScore;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String profileImgSrc;

    private String intro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<UserTechnologyStack> techStacks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "trust_score_id")
    TrustScore trustScore;

    @Builder
    private User(
            String email,
            String password,
            String nickname,
            String profileImgSrc,
            String intro,
            Position position,
            Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImgSrc = profileImgSrc;
        this.intro = intro;
        this.position = position;
        this.role = role;
    }

    // 기술스택 목록 등록
    public void setTechStacks(List<UserTechnologyStack> techStacks) {
        this.techStacks = techStacks;
    }

    // 신뢰점수 등록
    public void setTrustScore(TrustScore trustScore) {this.trustScore = trustScore;}
}

package com.example.demo.dto.trustscore.request;

import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TrustScoreUpdateRequestDto {
    @NotNull
    Long userId;
    Long projectId;
    Long milestoneId;
    Long workId;
    Boolean isPlus;
    Boolean isNewUser;
    Boolean isQuit;
    Boolean isForceQuit;
}
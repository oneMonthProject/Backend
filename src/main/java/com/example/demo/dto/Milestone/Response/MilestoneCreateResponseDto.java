package com.example.demo.dto.Milestone.Response;

import com.example.demo.dto.Project.Response.ProjectCreateResponseDto;
import com.example.demo.model.Milestone;
import com.example.demo.model.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MilestoneCreateResponseDto {
    private Long mileStoneId;
    private Long projectId;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean expireStatus;
    private boolean completeStatus;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static MilestoneCreateResponseDto of(Milestone milestone) {
        return MilestoneCreateResponseDto.builder()
                .mileStoneId(milestone.getId())
                .projectId(milestone.getProject().getId())
                .content(milestone.getContent())
                .startDate(milestone.getStartDate())
                .endDate(milestone.getEndDate())
                .expireStatus(milestone.isExpireStatus())
                .completeStatus(milestone.isCompleteStatus())
                .createDate(milestone.getCreateDate())
                .updateDate(milestone.getUpdateDate())
                .build();
    }
}
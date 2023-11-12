package com.example.demo.dto.project;

import com.example.demo.constant.ProjectStatus;
import com.example.demo.dto.board.BoardCreateResponseDto;
import com.example.demo.model.Board;
import com.example.demo.model.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProjectCreateResponseDto {
    private String name;
    private String subject;
    private long trustGradeId;
    private long userId;
    private ProjectStatus status;
    private int crewNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    public static ProjectCreateResponseDto of(Project project) {
        return ProjectCreateResponseDto.builder()
                .name(project.getName())
                .subject(project.getSubject())
                .trustGradeId(project.getTrustGrade().getId())
                .userId(project.getUser().getId())
                .status(project.getStatus())
                .crewNumber(project.getCrewNumber())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createDate(project.getCreateDate())
                .updateDate(project.getUpdateDate())
                .build();
    }
}

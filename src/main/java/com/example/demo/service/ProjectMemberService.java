package com.example.demo.service;

import com.example.demo.constant.AlertType;
import com.example.demo.global.exception.customexception.ProjectMemberCustomException;
import com.example.demo.model.Alert;
import com.example.demo.model.ProjectMember;
import com.example.demo.repository.AlertRepository;
import com.example.demo.repository.ProjectMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;
    private final AlertRepository alertRepository;

    /**
     * 프로젝트 멤버 탈퇴 알림 보내기
     *
     * @param projectMemberId
     */
    public void sendWithdrawlAlert(Long projectMemberId) {
        ProjectMember projectMember =
                projectMemberRepository
                        .findById(projectMemberId)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);

        Alert alert =
                Alert.builder()
                        .project(projectMember.getProject())
                        .user(projectMember.getUser())
                        .content("프로젝트 탈퇴")
                        .type(AlertType.WITHDRWAL)
                        .checked_YN(false)
                        .build();

        alertRepository.save(alert);
    }

    /**
     * 프로젝트 멤버 탈퇴 수락하기
     *
     * @param projectMemberId
     */
    public void withdrawlConfirm(Long projectMemberId) {
        ProjectMember projectMember =
                projectMemberRepository
                        .findById(projectMemberId)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);
        projectMemberRepository.delete(projectMember);
    }

    /**
     * 프로젝트 멤버 강제 탈퇴하기.
     *
     * @param projectMemberId
     */
    public void withdrawlForce(Long projectMemberId) {
        ProjectMember projectMember =
                projectMemberRepository
                        .findById(projectMemberId)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);
        projectMemberRepository.delete(projectMember);
    }
}

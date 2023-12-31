package com.example.demo.service.work;

import com.example.demo.dto.work.request.*;
import com.example.demo.dto.work.response.WorkPaginationResponseDto;
import com.example.demo.dto.work.response.WorkReadResponseDto;
import com.example.demo.global.exception.customexception.PageNationCustomException;
import com.example.demo.model.milestone.Milestone;
import com.example.demo.model.project.Project;
import com.example.demo.model.project.ProjectMember;
import com.example.demo.model.user.User;
import com.example.demo.model.work.Work;
import com.example.demo.service.milestone.MilestoneService;
import com.example.demo.service.project.ProjectMemberService;
import com.example.demo.service.project.ProjectService;
import com.example.demo.service.user.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkFacade {
    private final WorkService workService;
    private final ProjectService projectService;
    private final MilestoneService milestoneService;
    private final UserService userService;
    private final ProjectMemberService projectMemberService;

    public void create(
            Long userId, Long projectId, Long milestoneId, WorkCreateRequestDto workCreateRequestDto) {
        Project project = projectService.findById(projectId);
        Milestone milestone = milestoneService.findById(milestoneId);
        User user = userService.findById(userId);
        ProjectMember projectMember =
                projectMemberService.findProjectMemberByProjectAndUser(project, user);

        ProjectMember assignedProjectMember = projectMemberService.findById(workCreateRequestDto.getAssignedUserId());

        Work work = workCreateRequestDto.toWorkEntity(project, milestone, assignedProjectMember.getUser(), projectMember);

        workService.save(work);
    }

    @Transactional(readOnly = true)
    public WorkReadResponseDto getOne(Long workId) {
        Work work = workService.findById(workId);
        User assignedUser = work.getAssignedUserId();
        ProjectMember projectMember = projectMemberService.findProjectMemberByProjectAndUser(work.getProject(), assignedUser);

        return WorkReadResponseDto.of(work, projectMember, assignedUser);
    }

    @Transactional(readOnly = true)
    public List<WorkReadResponseDto> getAllByProject(Long projectId) {
        Project project = projectService.findById(projectId);
        List<Work> works = workService.findWorksByProject(project);

        List<WorkReadResponseDto> workReadResponseDtos = new ArrayList<>();
        for (Work work : works) {
            User assignedUser = work.getAssignedUserId();
            ProjectMember projectMember = projectMemberService.findProjectMemberByProjectAndUser(project, assignedUser);

            WorkReadResponseDto workReadResponseDto = WorkReadResponseDto.of(work, projectMember, assignedUser);
            workReadResponseDtos.add(workReadResponseDto);
        }

        return workReadResponseDtos;
    }

    @Transactional(readOnly = true)
    public WorkPaginationResponseDto getAllByMilestone(Long projectId, Long milestoneId, int pageIndex, int itemCount) {
        Project project = projectService.findById(projectId);
        Milestone milestone = milestoneService.findById(milestoneId);

        if(pageIndex < 0) {
            throw PageNationCustomException.INVALID_PAGE_NUMBER;
        }

        if(itemCount < 1 || itemCount > 6) {
            throw PageNationCustomException.INVALID_PAGE_ITEM_COUNT;
        }

        WorkPaginationResponseDto workPaginationResponse = workService
                .findWorksByProjectAndMilestone(project.getId(), milestone.getId(), PageRequest.of(pageIndex, itemCount));

        return workPaginationResponse;
    }

    /**
     * 업무 수정
     *
     * @param workId
     */
    public void update(Long userId, Long workId, WorkUpdateRequestDto workUpdateRequestDto) {
        Work work = workService.findById(workId);
        User user = userService.findById(userId);
        ProjectMember projectMember =
                projectMemberService.findProjectMemberByProjectAndUser(work.getProject(), user);

        // 할당된 회원 정보
        ProjectMember assignedUser = projectMemberService.findById(workUpdateRequestDto.getAssignedUserId());

        work.update(workUpdateRequestDto, projectMember, assignedUser.getUser());
    }

    /**
     * 업무 내용 수정 TODO : 마지막 변경자 바꿔줘야 함.
     *
     * @param workId
     * @param workUpdateContentRequestDto
     */
    public void updateContent(
            Long workId, WorkUpdateContentRequestDto workUpdateContentRequestDto) {
        Work work = workService.findById(workId);
        User user = userService.findById(1L);
        ProjectMember projectMember =
                projectMemberService.findProjectMemberByProjectAndUser(work.getProject(), user);
        work.updateContent(workUpdateContentRequestDto, projectMember);
    }

    /**
     * 업무 완료 여부 수정 TODO : 마지막 변경자 바꿔줘야 함.
     *
     * @param workId
     * @param workUpdateCompleteStatusRequestDto
     */
    public void updateCompleteStatus(
            Long workId, WorkUpdateCompleteStatusRequestDto workUpdateCompleteStatusRequestDto) {
        Work work = workService.findById(workId);
        User user = userService.findById(1L);
        ProjectMember projectMember =
                projectMemberService.findProjectMemberByProjectAndUser(work.getProject(), user);
        work.updateCompleteStatus(workUpdateCompleteStatusRequestDto, projectMember);
    }

    public void updateAssignUser(
            Long workId, WorkUpdateAssignUserRequestDto workUpdateAssignUserRequestDto) {
        Work work = workService.findById(workId);
        User user = userService.findById(workUpdateAssignUserRequestDto.getAssignUserId());
        ProjectMember projectMember =
                projectMemberService.findProjectMemberByProjectAndUser(work.getProject(), user);

        work.updateAssignedUserId(user, projectMember);
    }
}

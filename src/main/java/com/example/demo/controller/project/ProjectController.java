package com.example.demo.controller.project;

import com.example.demo.dto.common.ResponseDto;
import com.example.demo.dto.project.request.ProjectConfirmRequestDto;
import com.example.demo.dto.project.request.ProjectParticipateRequestDto;
import com.example.demo.dto.project.response.ProjectMeResponseDto;
import com.example.demo.dto.project.response.ProjectSpecificDetailResponseDto;
import com.example.demo.security.custom.PrincipalDetails;
import com.example.demo.service.project.ProjectFacade;
import com.example.demo.service.project.ProjectService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    public final ProjectService projectService;
    public final ProjectFacade projectFacade;

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<?>> getMyProjects(@AuthenticationPrincipal PrincipalDetails user) {
        List<ProjectMeResponseDto> result = projectFacade.getMyProjects(user.getId());
        return new ResponseEntity<>(ResponseDto.success("success", result), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ResponseDto<?>> getDetail(@PathVariable("projectId") Long projectId) {
        ProjectSpecificDetailResponseDto result = projectFacade.getDetail(projectId);
        return new ResponseEntity<>(ResponseDto.success("success", result), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/participate")
    public ResponseEntity<ResponseDto<?>> participate(
            @AuthenticationPrincipal PrincipalDetails user,
            @PathVariable("projectId") Long projectId,
            @RequestBody @Valid ProjectParticipateRequestDto projectParticipateRequestDto) {
        projectFacade.sendParticipateAlert(user.getId(), projectId, projectParticipateRequestDto);
        return new ResponseEntity<>(ResponseDto.success("success", null), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/participate/confirm")
    public ResponseEntity<ResponseDto<?>> confirm(
            @AuthenticationPrincipal PrincipalDetails user,
            @PathVariable("projectId") Long projectId,
            @RequestBody @Valid ProjectConfirmRequestDto projectConfirmRequestDto) {
        projectFacade.confirm(user.getId(), projectId, projectConfirmRequestDto);
        return new ResponseEntity<>(ResponseDto.success("success", null), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/end")
    public ResponseEntity<ResponseDto<?>> end(@PathVariable("projectId") Long projectId) {
        projectService.end(projectId);
        return new ResponseEntity<>(ResponseDto.success("success", null), HttpStatus.OK);
    }
}

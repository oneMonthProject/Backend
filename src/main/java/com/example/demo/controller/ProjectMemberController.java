package com.example.demo.controller;

import com.example.demo.dto.common.ResponseDto;
import com.example.demo.service.ProjectMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projectmember")
@AllArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping("/{projectMemberId}/withdrawl")
    public ResponseEntity<ResponseDto<?>> withdrawl(
            @PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.sendWithdrawlAlert(projectMemberId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PostMapping("/{projectMemberId}/withdrawl/confirm")
    public ResponseEntity<ResponseDto<?>> withdrawlConfirm(
            @PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.withdrawlConfirm(projectMemberId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PostMapping("/{projectMemberId}/withdrawl/force")
    public ResponseEntity<ResponseDto<?>> withdrawlForce(
            @PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.withdrawlForce(projectMemberId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }
}

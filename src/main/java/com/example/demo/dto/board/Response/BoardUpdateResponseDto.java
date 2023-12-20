package com.example.demo.dto.board.response;

import com.example.demo.global.util.LocalDateTimeFormatSerializer;
import com.example.demo.model.board.Board;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardUpdateResponseDto {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private long boardPageView;
    private boolean boardCompleteStatus;
    private long boardUserId;
    private String boardContact;

    @JsonSerialize(using = LocalDateTimeFormatSerializer.class)
    private LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeFormatSerializer.class)
    private LocalDateTime updateDate;

    public static BoardUpdateResponseDto of(Board board) {
        return BoardUpdateResponseDto.builder()
                .boardId(board.getId())
                .boardTitle(board.getTitle())
                .boardContent(board.getContent())
                .boardPageView(board.getPageView())
                .boardCompleteStatus(board.isCompleteStatus())
                .boardUserId(board.getUser().getId())
                .boardContact(board.getContent())
                .createDate(board.getCreateDate())
                .updateDate(board.getUpdateDate())
                .build();
    }
}

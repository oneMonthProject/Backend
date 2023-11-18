package com.example.demo.dto.Position.Response;

import com.example.demo.model.Position;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionResponseDto {
    private Long positionId;
    private String name;

    public static PositionResponseDto of(Position position) {
        return PositionResponseDto.builder()
                .positionId(position.getId())
                .name(position.getName())
                .build();
    }
}

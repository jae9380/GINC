package com.example.ginc.domain.batch.infrastructure.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationType {
    READ("조회"),
    REGISTER("등록"),
    UPDATE("수정"),
    DELETE("삭제");

    private final String koreanName;
}

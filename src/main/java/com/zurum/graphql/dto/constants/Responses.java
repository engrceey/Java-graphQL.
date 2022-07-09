package com.zurum.graphql.dto.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Responses {
    SUCCESS("Success"),
    FAILED("Failed"),
    PENDING("pending"),
    UNKNOWN("unkown");

    private final String status;
}
